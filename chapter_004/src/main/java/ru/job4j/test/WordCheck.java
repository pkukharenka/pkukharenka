package ru.job4j.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Программа сравнения двух строк посимвольно.
 *
 * @author Pyotr Kukharenka
 * @since 05.01.2018
 */

public class WordCheck {
    /**
     * Првоерка что одна строка содержит такие же символы
     * что и другая
     *
     * @param map    - структура для сравнения
     * @param first  - первая строка
     * @param second - вторая строка
     * @return - true если символы равны.
     */
    public boolean check(Map<Character, Integer> map, String first, String second) {
        long start = System.currentTimeMillis();
        boolean flag = false;
        char[] firstArr = first.toCharArray();
        char[] secArr = second.toCharArray();
        for (char c : firstArr) {
            map.computeIfPresent(c, (k, v) -> v + 1);
            map.putIfAbsent(c, 1);
        }
        for (char c : secArr) {
            if (map.containsKey(c)) {
                map.compute(c, (k, v) -> v - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        if (map.size() != 0) {
            flag = false;
        }
        System.out.printf("Время работы программы на основе %s - %s мс\n",
                map.getClass(), System.currentTimeMillis() - start);
        return flag;
    }

    /**
     * Возвращает строку из файла (для проверки работы программы на
     * больших строках).
     *
     * @param fileName - имя файла.
     * @return - строковое значение.
     */
    public String text(String fileName) {
        String text = null;
        URL res = WordCheck.class.getResource(fileName);
        try {
            File file = Paths.get(res.toURI()).toFile();
            InputStream fis = new FileInputStream(file);
            byte[] str = new byte[fis.available()];
            fis.read(str);
            text = new String(str);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return text;
    }

}
