package ru.job4j.storage;

/**
 * Класс User.
 *
 * @author Pyotr Kukharenka
 * @since 04.01.2018
 */

public class User {
    /**
     * ID пользователя.
     */
    private int id;
    /**
     * Количество денег на счете.
     */
    private int amount;

    /**
     * Конструктор для инициализации пользователя.
     *
     * @param id     - ID пользователя.
     * @param amount - количество денег на счете.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    //Геттеры и сеттеры, equals и hashCode.
    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
