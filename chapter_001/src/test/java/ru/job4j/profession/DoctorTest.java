package ru.job4j.profession;

import org.junit.Test;
import ru.job4j.profession.model.Doctor;
import ru.job4j.profession.model.Pacient;

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
        Doctor doctor = new Doctor("Иван", "Демидов");
        Pacient pacient = new Pacient("Дмитрий", "Демидов");
        final String res = doctor.heal(pacient);
        final String exp = "Доктор Иван лечит Дмитрий";
        assertThat(res, is(exp));
    }

    @Test
    public void whenDoctorConsultThenExpressionTrue() {
        Doctor doctor = new Doctor("Денис", "Демидов");
        Doctor badDoctor = new Doctor("Валера", "Демидов");
        final String res = doctor.consult(badDoctor);
        final String exp = "Доктор Денис проводит консултацию с доктором Валера";
        assertThat(res, is(exp));
    }
}