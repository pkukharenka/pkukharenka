package ru.job4j.test;

/**
 * Объект типа заявка.
 *
 * @author Pyotr Kukharenka
 * @since 29.12.2017
 */

public class Order {
    /**
     * Уникальный номер заявки.
     */
    private int id;
    /**
     * Наименование книги.
     */
    private String book;
    /**
     * Цена книги.
     */
    private double price;
    /**
     * Количество книг в предложении
     */
    private int volume;
    /**
     * Предложение или спрос.
     */
    private String operation;

    /**
     * Пустой конструктор.
     */
    public Order() {
    }

    /**
     * Конструктор для инициализации стоимости и количества
     * (для тестирования метода checkPrice)
     *
     * @param price - стоимость.
     * @param volume - количество.
     */
    public Order(double price, int volume) {
        this.price = price;
        this.volume = volume;
    }

    //Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
