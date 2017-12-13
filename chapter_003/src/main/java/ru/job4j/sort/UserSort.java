package ru.job4j.sort;

import java.util.*;

/**
 * Класс, реализующий сортировку коллекции по условию компоратора
 * класса User (по возрастанию возраста).
 *
 * @author Pyotr Kukharenka
 * @since 12.12.2017
 */

public class UserSort {

    /**
     * Метод, реализующий передачу элементов коллекции типа List в
     * коллекцию типа Set и ее сортровку по возрастанию возраста
     * пользователей за счет реализации аннонимного класса Comparator.
     * В компараторе производится сравнения возрастов пользователей,
     * в случае если у нескольких пользователей возраст оказывается
     * одинаковым проверяются имена.
     *
     * @param list - входящая коллекция.
     * @return - отсортиованный Set.
     */
    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<>(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int result = ((Integer) o1.getAge()).compareTo(o2.getAge());
                        return result != 0 ? result : o1.getName().compareTo(o2.getName());
                    }
                });
        users.addAll(list);
        return users;
    }

    /**
     * Main метод программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        UserSort us = new UserSort();
        List<User> list = new ArrayList<>();
        list.addAll(
                Arrays.asList(
                        new User("ivan", 15),
                        new User("petr", 18),
                        new User("jora", 5),
                        new User("zuma", 13),
                        new User("artem", 13)
                )
        );
        System.out.println(us.sort(list));
    }
}
