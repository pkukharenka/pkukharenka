package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Простой объект
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */
@XmlRootElement(name = "entry")
public class Entry {
    /**
     * Поле.
     */
    private int field;

    /**
     * Пустой конструктор для работы JAXB.
     */
    public Entry() {
    }

    /**
     * Конструктор для инициализации поля.
     *
     * @param field - поле.
     */
    public Entry(int field) {
        this.field = field;
    }

    //Геттер
    @XmlElement
    public int getField() {
        return field;
    }
}
