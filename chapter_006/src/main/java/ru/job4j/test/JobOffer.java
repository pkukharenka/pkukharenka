package ru.job4j.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * Основная логика работы программы по поиску вакансий Java
 *
 * @author Pyotr Kukharenka
 * @since 10.02.2018
 */

public class JobOffer implements Runnable {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(JobOffer.class);
    /**
     * Стартовая ссылка с вакансиями.
     */
    private static final String URL = "http://www.sql.ru/forum/job-offers/";
    /**
     * Структура для хранения объектов, подходящих для записи в базу.
     */
    private final ConcurrentHashMap<Offer, Object> valid = new ConcurrentHashMap<>();

    /**
     * Добавление объекта в map.
     *
     * @param offer - объект
     */
    public void add(Offer offer) {
        this.valid.put(offer, new Object());
    }

    /**
     * Запись объектов из map в базу данных.
     * 1. Получаем все записи из базы данных
     * 2. Удаляем из новых записей дубликаты
     * 3. Записываем новые данные в базу.
     *
     * @return - количество новых записей.
     */
    private int toBase() {
        Util.create();
        HashSet<Offer> offers = this.fromBase();
        LOG.info(String.format("Всего вакансий в базе - %s.", offers.size()));
        this.valid.keySet().removeAll(offers);
        LOG.info(String.format("Найдено новых уникальных вакансий - %s", this.valid.size()));
        try (Connection cn = Util.getConnection()) {
            cn.setAutoCommit(false);
            PreparedStatement ps = cn.prepareStatement("INSERT INTO offer (title, content, create_date, url) VALUES (?, ?, ?, ?)");
            for (Offer offer : this.valid.keySet()) {
                ps.setString(1, offer.getTitle());
                ps.setString(2, offer.getText());
                ps.setDate(3, Date.valueOf(offer.getCreate()));
                ps.setString(4, offer.getUrl());
                ps.addBatch();
            }
            ps.executeBatch();
            cn.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.valid.size();
    }

    /**
     * Возвращает спсиок записей из базы данных.
     *
     * @return - список записей
     */
    private HashSet<Offer> fromBase() {
        LOG.info("Извлекаем данные из базы...");
        HashSet<Offer> offers = new HashSet<>(100);
        try (
                Connection cn = Util.getConnection();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM offer")
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String text = rs.getString("content");
                LocalDate date = LocalDate.parse(rs.getString("create_date"));
                String link = rs.getString("url");
                offers.add(new Offer(id, title, text, date, link));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return offers;
    }

    /**
     * Основной поток работы программы.
     * 1. Настриваем ExecutorServise (количество потоков берем из файла настроек);
     * 2. Считаем количество страниц, в которых будет производиться поиск
     * вакансий
     * 3. Получаем список задач
     * 4. Обрабатываем задачи ExecutorService-ом, с помощью Strem API получаем
     * количество успешно завешенных потоков и сравниваем это количество
     * с количеством страниц для обработки.
     * 5. Если есть новые вакансии, записываем их в базу.
     * 6. Меняем дату запуска в файле настроек.
     */
    public void run() {
        LOG.info(String.format("Начало работы - %s", LocalDateTime.now().toString()));
        Properties properties = Util.getProperties();
        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(properties.getProperty("threads")));
        int pages = this.pagesForSearch();
        List<Callable<String>> tasks = this.fillTasks(pages);
        try {
            if (executor.invokeAll(tasks).stream().map(Future::isDone).count() == pages) {
                LOG.info(String.format("Все потоки отработали, закрываем пул. Найдено вакансий - %s", this.valid.size()));
                executor.shutdown();
                int newOffer = 0;
                if (this.valid.size() > 0) {
                    newOffer = this.toBase();
                }
                LOG.info("Обновляем последнюю дату запуска");
                Util.updateProperty(LocalDate.now().minusDays(1));
                LOG.info(String.format("Добавлено новых записей - %s. Конец работы - %s", newOffer, LocalDateTime.now().toString()));
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Возвращает список задач (страниц) для обработки
     *
     * @param pages - количество страниц
     * @return - список задач
     */
    private List<Callable<String>> fillTasks(int pages) {
        List<Callable<String>> tasks = new ArrayList<>();
        LOG.info(String.format("Количество страниц для обработки - %s", String.valueOf(pages)));
        for (int i = 1; i <= pages; i++) {
            tasks.add(new Parser(String.format("%s%s", URL, i), this));
        }
        return tasks;
    }

    /**
     * Возвращает общее количество страниц форума вакансий.
     *
     * @return - количество страниц форума вакансий.
     */
    private int allPages() {
        int count = 0;
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements tds = doc.getElementsByTag("td");
            for (Element td : tds) {
                if (td.text().matches("Страницы.*")) {
                    Elements a = td.getAllElements();
                    count = Integer.parseInt(a.last().text());
                }
            }
        } catch (NumberFormatException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return count;
    }

    /**
     * Возвращает количество страниц которые будут обработаны.
     * Основная логика метода:
     * Проверяем дату последней темы на каждой странице до тех пор,
     * пока дата будет больше крайней даты запуска программы минус
     * количнство месяцев из файла настроеек (по уолчанию 12)
     *
     * @return - количество страниц которые будут обработаны
     */
    private int pagesForSearch() {
        Properties props = Util.getProperties();
        LocalDate limit = LocalDate.parse(props.getProperty("limit"));
        int month = Integer.parseInt(props.getProperty("month"));
        int all = this.allPages();
        int pages = 0;
        try {
            for (int i = 1; i <= all; i++) {
                final Document doc = Jsoup.connect(URL + i).get();
                final Element last = doc.getElementsByClass("postslisttopic").last();
                String link = last.getElementsByTag("a").attr("href");
                final Document lastTopic = Jsoup.connect(link).get();
                final Element firstPost = lastTopic.getElementsByClass("msgTable").first();
                final LocalDate date = Util.getDate(firstPost);
                LocalDate min = limit.minusMonths(month);
                if (!date.isAfter(min)) {
                    pages = i;
                    break;
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return pages;
    }

}


