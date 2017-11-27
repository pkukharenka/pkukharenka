package ru.job4j.profession;

/**
 * Class that create simple doctor
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class Doctor extends Profession {
    private String degree;

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
