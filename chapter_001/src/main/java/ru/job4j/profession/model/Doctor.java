package ru.job4j.profession.model;

import ru.job4j.profession.Profession;

/**
 * Class that create simple doctor
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Doctor extends Profession {
    private String degree;

    /**
     * Base constructor.
     *
     * @param name    - person name.
     * @param surname - person surname.
     */
    public Doctor(String name, String surname) {
        super(name, surname);
    }



    /**
     * Heal pacient.
     *
     * @param pacient - simple pacient
     * @return - expression.
     */
    public String heal(Pacient pacient) {
        return "Доктор " + getName() + " лечит " + pacient.getName();
    }

    /**
     * Consulting bad doctor
     *
     * @param doctor - simple doctor.
     * @return - expression.
     */
    public String consult(Doctor doctor) {
        return "Доктор " + getName() + " проводит консултацию с доктором " + doctor.getName();
    }
}
