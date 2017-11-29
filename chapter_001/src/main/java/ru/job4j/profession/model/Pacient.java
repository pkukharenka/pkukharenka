package ru.job4j.profession.model;

import ru.job4j.profession.Profession;

/**
 * Simple pacient
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Pacient extends Profession {

    /**
     * Base constructor.
     *
     * @param name    - person name.
     * @param surname - person surname.
     */
    public Pacient(String name, String surname) {
        super(name, surname);
    }
}
