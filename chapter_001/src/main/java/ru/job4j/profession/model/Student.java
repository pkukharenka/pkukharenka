package ru.job4j.profession.model;

import ru.job4j.profession.Profession;

/**
 * Simple Student
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Student extends Profession {

    /**
     * Base constructor.
     *
     * @param name    - person name.
     * @param surname - person surname.
     */
    public Student(String name, String surname) {
        super(name, surname);
    }
}
