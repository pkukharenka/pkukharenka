package ru.job4j.jdbc;

/**
 * JavaBean объект.
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */

public class Bean {
    /**
     * Адресс подключения к базе.
     */
    private String url;
    /**
     * Имя пользователя базы.
     */
    private String userName;
    /**
     * Пароль базы
     */
    private String password;
    /**
     * Количество значений для добавления в базу.
     */
    private int size;

    /**
     * Конструктор.
     */
    public Bean() {
    }

    //Геттеры и сеттеры
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
