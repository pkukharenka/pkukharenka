package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест конвертации коллекции Users в карту.
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */
public class UserConvertTest {

    /**
     * Тест проверки конвертации коллекции Users в карту.
     * Добавляем 4 пользователя в коллекцию. Производим
     * конвертацию, размер карты должен быть 4.
     */
    @Test
    public void whenListOfUsersConvertToMapThenSizeFour() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Petr", "Minsk"));
        list.add(new User(2, "Ivan", "Grodno"));
        list.add(new User(3, "Olga", "Voronej"));
        list.add(new User(4, "Artem", "Gomel"));
        UserConvert convert = new UserConvert();
        assertThat(convert.process(list).size(), is(4));
    }

}