package ru.job4j.profession;

import java.util.ArrayList;

/**
 * Class that describe simple teacher.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Teacher extends Profession {
    private String degree;

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
