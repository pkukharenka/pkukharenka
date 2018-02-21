package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Утилитный класс для загрузки параметров программы.
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class Settings {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class);
    /**
     * Инстанс класса.
     */
    private static final Settings INSTANCE = new Settings();
    /**
     * Настройки класса.
     */
    private final Properties properties = new Properties();

    /**
     * Приватный конструктор инициализирующий настройки программы.
     */
    private Settings() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("crud.properties").getFile()));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Возвращает инстанс класса.
     * @return - инстанс класса.
     */
    public static Settings getInstance() {
        return INSTANCE;
    }

    /**
     * Возвращает значение свойства программы по ключу.
     * @param key - ключ свойства.
     * @return - значение свойства программы
     */
    public String property(String key) {
        return properties.getProperty(key);
    }
}
