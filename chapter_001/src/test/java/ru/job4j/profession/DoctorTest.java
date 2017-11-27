package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Doctor tests.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class DoctorTest {
    @Test
    public void whenDoctorHealThenExpressionTrue() {
        Doctor doctor = new Doctor();
        Pacient pacient = new Pacient();
        doctor.setName("Иван");
        pacient.setName("Дмитрий");
        final String res = doctor.heal(pacient);
        final String exp = "Доктор Иван лечит Дмитрий";
        assertThat(res, is(exp));
    }

    @Test
    public void whenDoctorConsultThenExpressionTrue() {
        Doctor doctor = new Doctor();
        Doctor badDoctor = new Doctor();
        doctor.setName("Денис");
        badDoctor.setName("Валера");
        final String res = doctor.consult(badDoctor);
        final String exp = "Доктор Денис проводит консултацию с доктором Валера";
        assertThat(res, is(exp));
    }
}