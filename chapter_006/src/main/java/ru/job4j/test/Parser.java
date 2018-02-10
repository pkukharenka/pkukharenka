package ru.job4j.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Парсер вакансий Java с сайта sql.ru
 *
 * @author Pyotr Kukharenka
 * @since 09.02.2018
 */

public class Parser implements Callable<String> {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

    /**
     * Основной класс программы
     */
    private final JobOffer job;
    /**
     * Начальная ссылка для поиска вакансий
     */
    private final String url;

    /**
     * Конструктор для инициализации параметров
     *
     * @param url - Начальная ссылка для поиска вакансий
     * @param job - Основной класс программы
     */
    public Parser(String url, JobOffer job) {
        this.url = url;
        this.job = job;
    }

    /**
     * Возвращает объект с информацией по вакансии Java. Из передаваемой
     * ссылки извлекаем первый пост "msgTable.first", далее из полученного
     * объекта получаем дату размещения сообщения, текст сообщения и тему
     * сообщения. Проверяем чтобы дата темы была в пределах установленного лимита
     *
     * @param link  - ссылка на страницу, которая соответствует вакансии Java
     * @param limit - ограничение по дате объявления
     * @return - объект для записи в базу данных
     * @throws IOException - выбрасывается в случае ошибки считывания данных
     */
    private Offer checkDate(final String link, final LocalDate limit) throws IOException {
        Offer offer = null;
        final Document javaDoc = Jsoup.connect(link).get();
        final Element main = javaDoc.getElementsByClass("msgTable").first();
        final LocalDate date = Util.getDate(main);
        if (date.isAfter(limit)) {
            final String text = Util.getText(main);
            final String title = Util.getTitle(main);
            offer = new Offer(title, text, date, link);
        }
        return offer;
    }

    /**
     * Метод обрабатывает вакансии Java по ссылке. Подключаемся по ссылке
     * получаем список тем форума, и если заголовок темы соответсвует вакансии
     * Java обрабатываем эту тему. Полученный объект добавляем в базу данных.
     *
     * @return - возвращает стрковое значение для индикации окончания работы потока.
     */
    @Override
    public String call() {
        final Properties props = Util.getProperties();
        final String limit = props.getProperty("limit");
        try {
            final Document doc = Jsoup.connect(this.url).get();
            final Elements table = doc.getElementsByClass("postslisttopic");
            for (Element href : table) {
                if (href.text().replaceAll("\\s", "").matches(".*[jJ]ava(?![sS]cript).*")) {
                    final String link = href.getElementsByTag("a").first().attr("href");
                    final Offer offer = this.checkDate(link, LocalDate.parse(limit));
                    if (offer != null) {
                        this.job.add(offer);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return "done";
    }

}
