package ru.job4j.bank.model;

/**
 * Класс, описывающий счет клиента в банке. Каждый клиент
 * может иметь неограниченное количество счетов в банке.
 *
 * @author Pyotr Kukharenka
 * @since 13.12.2017
 */

public class Account {
    /**
     * Количество денежных средств на счете клиента.
     */
    private double value;
    /**
     * Реквизиты счета клиента
     */
    private String requisites;

    /**
     * Конструктор инициализирующий юанковский счет.
     *
     * @param value
     * @param requisites
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }
}
