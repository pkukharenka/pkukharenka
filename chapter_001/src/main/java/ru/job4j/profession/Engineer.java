package ru.job4j.profession;

/**
 * Class that describe engineer.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Engineer extends Profession {

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
