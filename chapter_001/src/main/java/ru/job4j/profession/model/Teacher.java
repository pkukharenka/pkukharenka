package ru.job4j.profession.model;

import ru.job4j.profession.Profession;

/**
 * Class that describe simple teacher.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Teacher extends Profession {
    private String degree;

    /**
     * Base constructor.
     *
     * @param name    - person name.
     * @param surname - person surname.
     */
    public Teacher(String name, String surname) {
        super(name, surname);
    }


    /**
     * Teach student.
     *
     * @param student - simple student
     * @return - expression.
     */
    public String teach(Student student) {
        return "Учитель " + getName() + " учит " + student.getName();
    }
}
