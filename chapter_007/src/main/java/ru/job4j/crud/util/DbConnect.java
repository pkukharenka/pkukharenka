package ru.job4j.crud.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Утилитный класс для коннекта к базе данных
 * с использованием пула соединений
 *
 * @author Pyotr Kukharenka
 * @since 02.03.2018
 */

public class DbConnect {

    private static final Logger LOG = LoggerFactory.getLogger(DbConnect.class);
    /**
     * Инстанс класса.
     */
    private final static DbConnect INSTANCE = new DbConnect();
    /**
     * Пул потоков
     */
    private final BasicDataSource dataSource;

    private DbConnect() {
        final Settings settings = Settings.getInstance();
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName(settings.property("driver"));
        this.dataSource.setUrl(settings.property("url"));
        this.dataSource.setUsername(settings.property("name"));
        this.dataSource.setPassword(settings.property("password"));
        this.createBaseStructure();
    }

    public static DbConnect getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    private void createBaseStructure() {
        try (
                Connection cn = this.dataSource.getConnection();
                Statement st = cn.createStatement();
        ) {
            InputStream is = getClass().getClassLoader().getResourceAsStream("crud.sql");
            cn.setAutoCommit(false);
            Scanner sc = new Scanner(is);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                st.addBatch(sc.next());
            }
            st.executeBatch();
            cn.commit();
            LOG.info("DataBase Structure is done!");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void closeData() {
        try {
            this.dataSource.close();
            LOG.info("Датасорс закрыт - {}", this.dataSource.isClosed());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
