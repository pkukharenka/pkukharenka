package ru.job4j.start.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.start.tracker.io.Input;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Утилитный класс для подключения к базе данных
 * и отключения от нее.
 *
 * @author Pyotr Kukharenka
 * @since 28.01.2018
 */

public class Util {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);
    /**
     * Соединение к базе
     */
    private Connection connection;

    /**
     * Получение соединения к базе. Настройки подключения берутся
     * из файла .properties
     *
     * @return - connection к базе
     */
    public Connection getConnection() {
        if (this.connection == null) {
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("db");
                String url = bundle.getString("jdbc.url");
                String username = bundle.getString("jdbc.username");
                String password = bundle.getString("jdbc.password");
                this.connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return this.connection;
    }

    /**
     * Отключение от базы
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }

    /**
     * Закрытие ResultSet.
     *
     * @param rs - ResultSet
     */
    public void setClose(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Закрытие Statement.
     *
     * @param st - Statement.
     */
    public void statementClose(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Выполнение SQL скриптов по создании структуры базы данных
     * при ее отсутствии.
     */
    public void checkTable() {
        Statement st = null;
        InputStream is = null;
        try {
            st = this.getConnection().createStatement();
            is = ClassLoader.getSystemResourceAsStream("init.sql");
            Scanner sc = new Scanner(is);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String line = sc.next().trim();
                if (!line.isEmpty()) {
                    st.execute(line);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            this.statementClose(st);
            this.disconnect();
        }
    }
}
