package ru.job4j.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Объект, описывающий вакансию
 *
 * @author Pyotr Kukharenka
 * @since 10.02.2018
 */

public class Offer {
    private static final Logger LOG = LoggerFactory.getLogger(Offer.class);
    private int id;
    /**
     * Заголовок вакансии
     */
    private String title;
    /**
     * Текст вакансии
     */
    private String text;
    /**
     * Дата размещения вакансии
     */
    private LocalDate create;
    /**
     * Ссылка на тему форума.
     */
    private String url;

    public Offer(int id, String title, String text, LocalDate create, String url) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.create = create;
        this.url = url;
    }

    public Offer(String title, String text, LocalDate create, String url) {
        this.title = title;
        this.text = text;
        this.create = create;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }


    public String getText() {
        return text;
    }


    public LocalDate getCreate() {
        return create;
    }


    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Offer offer = (Offer) o;

        if (text != null ? !text.equals(offer.text) : offer.text != null) {
            return false;
        }
        return create != null ? create.equals(offer.create) : offer.create == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (create != null ? create.hashCode() : 0);
        return result;
    }
}
