package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Объект, объеденяющий в себе объекты типа Entry.
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */
@XmlRootElement
public class Entries {
    /**
     * Список объектов типа Entry
     */
    private List<Entry> entries;

    /**
     * Конструктор для инициализации списка.
     */
    public Entries() {
        this.entries = new ArrayList<>(1000000);
    }

    /**
     * Возвращает список элементов.
     *
     * @return - список элементов.
     */
    @XmlElement(name = "entry")
    public List<Entry> getEntries() {
        return entries;
    }

}
