package ru.job4j.convert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Класс, реализующий преобразование коллекции Users в
 * карту Users с ключом - Id.
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */

public class UserConvert {
    /**
     * Метод производящий преобразование коллекции Users в
     * карту Users с ключом - Id.
     *
     * @param list - входящая коллекция.
     * @return карта.
     */
    public Map<Integer, User> process(List<User> list) {
        Map<Integer, User> users = new HashMap<>();
        for (User user : list) {
            users.put(user.getId(), user);
        }
        return users;
    }
}
