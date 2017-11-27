package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Engineer tests.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */
public class EngineerTest {

    @Test
    public void whenCreateThen() {
        Engineer engineer = new Engineer();
        Worker worker = new Worker();
        engineer.setName("Иван");
        worker.setName("Дмитрий");
        final String res = engineer.create(worker);
        final String exp = "Рабочий Дмитрий под руководством Иван создает деталь.";
        assertThat(res, is(exp));
    }

    @Test
    public void whenDevelopThen() {
        Engineer engineer = new Engineer();
        engineer.setName("Денис");
        final String res = engineer.develop("здание");
        final String exp = "Инженер Денис проектирует здание";
        assertThat(res, is(exp));
    }
}