package ru.job4j.test;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.Scanner;

import org.jsoup.nodes.Element;

/**
 * Утилитный класс.
 *
 * @author Pyotr Kukharenka
 * @since 09.02.2018
 */

public class Util {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);
    /**
     * Файл с настройками программы
     */
    private static final String PROPERTIES = "parser.properties";
    /**
     * Файл со скриптами для создания базы данных.
     */
    private static final String SQL = "parser.sql";
    /**
     * Текущее время
     */
    public static final LocalDate NOW = LocalDate.now();
    /**
     * Маска для парсинга даты из строки.
     */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d MMM yy");

    /**
     * Возвращает объект properties с настройками программы
     *
     * @return - Properties.
     */
    public static Properties getProperties() {
        Properties props = new Properties();
        try (InputStream fis = ClassLoader.getSystemResourceAsStream(PROPERTIES)) {
            props.load(fis);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return props;
    }

    /**
     * Записывает в Properties изменения даты последнего
     * запуска программы.
     *
     * @param date - дата последнего запуска программы.
     */
    public static void updateProperty(LocalDate date) {
        Properties properties = getProperties();
        properties.setProperty("limit", date.toString());
        File file = new File(ClassLoader.getSystemResource(PROPERTIES).getFile());
        try (OutputStream fos = new FileOutputStream(file)) {
            properties.store(fos, null);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Возвращает конект к базе данных.
     *
     * @return - коннект к базе данных
     * @throws SQLException - выбрасывается в случаях задания неверных
     *                      параметров подключения
     */
    public static Connection getConnection() throws SQLException {
        Properties props = getProperties();
        final String url = props.getProperty("jdbc.url");
        final String username = props.getProperty("jdbc.username");
        final String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Метод выполняет скрипты из файла.
     */
    public static void create() {
        try (
                Connection cn = getConnection();
                Statement st = cn.createStatement();
                InputStream fis = ClassLoader.getSystemResourceAsStream(SQL)
        ) {
            Scanner sc = new Scanner(fis);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                st.execute(sc.next());
            }
        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Возвращает заголовок темы по вакансии.
     *
     * @param main - первый пост в теме.
     * @return - заголовок темы по вакансии.
     */
    public static String getTitle(Element main) {
        final Elements header = main.getElementsByClass("messageHeader");
        return header.text();

    }
    /**
     * Возвращает текст вакансии.
     *
     * @param main - первый пост в теме.
     * @return - текст вакансии.
     */
    public static String getText(Element main) {
        final Elements body = main.getElementsByClass("msgBody");
        return body.last().text();
    }

    /**
     * Возвращает дату в формате LocalDate.
     * 4 основных момента:
     * 1. Дату получаем из футера первого поста в теме.
     * 2. Если в дате фигурирует  слово "Сегодня", возвращаем
     * текущее время
     * 3. Если в дате фигурирует  слово "Вчера", возвращаем
     * текущее время и отнимаем 1 день
     * 4. Во всех остальных случаях парсим с использованием шаблона,
     * если в строке есть слово "май" -> меняем его на "мая"
     *
     * @param main - первый пост в теме.
     * @return - дата в формате LocalDate
     */
    public static LocalDate getDate(Element main) {
        final Elements footer = main.getElementsByClass("msgFooter");
        final String[] time = footer.first().text().split(",");
        String s = time[0];
        LocalDate date = null;
        if (s.matches("[Сс]егодня.*")) {
            date = NOW;
        } else if (s.matches("[Вв]чера.*")) {
            date = NOW.minusDays(1);
        } else {
            try {
                s = s.replaceAll("май", "мая");
                date = LocalDate.parse(s, FORMAT);
            } catch (DateTimeParseException e) {
                LOG.error(e.getParsedString(), e);
            }
        }
        return date;
    }
}
