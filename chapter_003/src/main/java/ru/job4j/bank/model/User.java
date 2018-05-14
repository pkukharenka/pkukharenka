package ru.job4j.bank.model;

/**
 * Класс, описывающий клиента банка. Каждый клиент имеет
 * имя и паспорт.
 *
 * @author Pyotr Kukharenka
 * @since 13.12.2017
 */

public class User {
    /**
     * Имя клиента.
     */
    private String name;
    /**
     * Номер паспорта клиента
     */
    private String passport;

    /**
     * Конструктор, инициализирующий нового клиента
     *
     * @param name     - имя клиента.
     * @param passport - номер паспорта клиента.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

}
