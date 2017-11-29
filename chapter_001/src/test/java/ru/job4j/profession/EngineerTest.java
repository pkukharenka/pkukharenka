package ru.job4j.profession;

import org.junit.Test;
import ru.job4j.profession.model.Engineer;
import ru.job4j.profession.model.Worker;

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
        Engineer engineer = new Engineer("Иван", "Демидов");
        Worker worker = new Worker("Дмитрий", "Иванов");
        final String res = engineer.create(worker);
        final String exp = "Рабочий Дмитрий под руководством Иван создает деталь.";
        assertThat(res, is(exp));
    }

    @Test
    public void whenDevelopThen() {
        Engineer engineer = new Engineer("Денис", "Демидов");
        final String res = engineer.develop("здание");
        final String exp = "Инженер Денис проектирует здание";
        assertThat(res, is(exp));
    }
}