package ru.job4j.perfomance;

import java.util.*;

/**
 * Программа для тестирования производительности коллекций
 * ArrayList, LinkedList на добавления и удаление элементов.
 *
 * @author Pyotr Kukharenka
 * @since 10.12.2017
 */

public class Perfomance {
    /**
     * Метод для добавления элементов в коллекцию
     *
     * @param collection - коллекция, в которую будут добавляться
     *                   элементы.
     * @param amount     - количество элементов для добавления
     * @return время за которое выполнился метод.
     */
    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        String test = "test";
        for (int index = 0; index <= amount; index++) {
            collection.add(test);
        }
        return System.currentTimeMillis() - start;
    }

    /**
     * Метод для удаления первых n элементов из коллекции.
     *
     * @param collection - коллекция, из которой будут удаляться
     *                   элементы.
     * @param amount     - количество элементов для удаления
     * @return время за которое выполнился метод.
     */
    public long delete(Collection<String> collection, int amount) {
        int number = 1000000;
        this.add(collection, number);
        long start = System.currentTimeMillis();
        for (int index = 0; index <= amount; index++) {
            collection.remove("test");
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        //Коллекция ArrayList.
        List<String> array = new ArrayList<>();
        //Коллекция LinkedList.
        List<String> linked = new LinkedList<>();
        //Коллекция TreeSet.
        Set<String> set = new TreeSet<>();

        Perfomance perf = new Perfomance();

        int add = 1000000;
        long arrayAdd = perf.add(array, add);
        long linkedAdd = perf.add(linked, add);
        long setAdd = perf.add(set, add);
        System.out.println(String.format("Test for adding %s items in collections:\nArrayList - %s ms"
                + "\nLinkedList - %s ms\nTreeSet - %s ms", add, arrayAdd, linkedAdd, setAdd));
        int del = 9000;
        long arrayDelete = perf.delete(array, del);
        long linkedDelete = perf.delete(linked, del);
        long setDelete = perf.delete(set, del);
        System.out.println(String.format("\nTest for delete first %s items in collections:\nArrayList - %s ms\n"
                + "LinkedList - %s ms\nTreeSet - %s ms", del, arrayDelete, linkedDelete, setDelete));


    }

}

