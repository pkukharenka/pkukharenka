package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Teacher tests.
 *
 * @author Pyotr Kukharenka
 * @since 27.11.2017
 */

public class TeacherTest {
    @Test
    public void whenTeacherTeachThenExpressionTrue() {
        Teacher teacher = new Teacher();
        Student student = new Student();
        teacher.setName("Валентина");
        student.setName("Анна");
        final String res = teacher.teach(student);
        final String exp = "Учитель Валентина учит Анна";
        assertThat(res, is(exp));
    }

}