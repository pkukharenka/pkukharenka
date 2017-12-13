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
     * пользователей за счет реализации метода compareTo интерфейса
     * Comparable у класса User.
     *
     * @param list - входящая коллекция.
     * @return - отсортиованный Set.
     */
    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<>();
        users.addAll(list);
        return users;
    }

    /**
     * Метод производит сортировку входящей коллекции по длине
     * имени пользователей. В случае если длинна имен одинаковая
     * проивзлдится сравнение их возрастов. Основная логика заключена
     * в тернарном условии.
     *
     * @param users - входящая коллекция для сортировки.
     * @return - отсортированная коллекиця.
     */
    public List<User> sortNameLenght(List<User> users) {
        List<User> list = new ArrayList<>(users);
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int res = ((Integer) o1.getName().length()).compareTo(o2.getName().length());
                return res != 0 ? res : ((Integer) o1.getAge()).compareTo(o2.getAge());
            }
        });
        return list;
    }

    /**
     * Метод производит сортировку входящей коллекции по всем полям
     * пользлвателей.
     *
     * @param users - входящая коллекция для сортировки.
     * @return - отсортированная коллекиця.
     */
    public List<User> sortByAllFields(List<User> users) {
        List<User> list = new ArrayList<>(users);
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int res = o1.getName().compareTo(o2.getName());
                return res != 0 ? res : ((Integer) o1.getAge()).compareTo(o2.getAge());
            }
        });
        return list;
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
                        new User("olga", 15),
                        new User("viktor", 18),
                        new User("olga", 5),
                        new User("gennadiy", 13),
                        new User("andrey", 13)
                )
        );
        System.out.println(us.sortNameLenght(list));
        System.out.println(us.sortByAllFields(list));
    }
}
