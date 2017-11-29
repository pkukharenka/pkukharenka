package ru.job4j.profession.model;

import ru.job4j.profession.Profession;

/**
 * Class that describe engineer.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Engineer extends Profession {


    /**
     * Base constructor.
     *
     * @param name    - person name.
     * @param surname - person surname.
     */
    public Engineer(String name, String surname) {
        super(name, surname);
    }

    /**
     * Engineer create something with simple worker.
     *
     * @param worker - simple worker.
     * @return - expression
     */
    public String create(Worker worker) {
        return "Рабочий " + worker.getName() + " под руководством " + getName() + " создает деталь.";
    }

    /**
     * Engineer develops something.
     *
     * @param task - task for developing.
     * @return - expression.
     */
    public String develop(String task) {
        return "Инженер " + getName() + " проектирует " + task;
    }
}
