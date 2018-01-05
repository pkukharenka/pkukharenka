package ru.job4j.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class WordCheckTest {

    @Test
    public void whenAddTwoWordsWithSameCharsThenTrue() {
        String first = "мама";
        String second = "амам";
        Map<Character, Integer> hashMap = new HashMap<>();
        WordCheck wordCheck = new WordCheck();
        assertTrue(wordCheck.check(hashMap, first, second));

    }

    @Test
    public void whenCompareTwoHugeStringsThenHashFasterThenTree() {
        Map<Character, Integer> treeMap = new TreeMap<>();
        Map<Character, Integer> hashMap = new HashMap<>();
        WordCheck wordCheck = new WordCheck();

        String first = wordCheck.text("/test.txt");
        String second = wordCheck.text("/test.txt");
        System.out.printf("Количество символов - %s\n", first.length());
        assertTrue(wordCheck.check(hashMap, first, second));
        assertTrue(wordCheck.check(treeMap, first, second));

    }

    @Test
    public void whenCompareTwoMediumStringsThenTreeFasterThanHash() {
        Map<Character, Integer> treeMap = new TreeMap<>();
        Map<Character, Integer> hashMap = new HashMap<>();
        WordCheck wordCheck = new WordCheck();

        String first = wordCheck.text("/test2.txt");
        String second = wordCheck.text("/test2.txt");
        System.out.printf("Количество символов - %s\n", first.length());
        assertTrue(wordCheck.check(hashMap, first, second));
        assertTrue(wordCheck.check(treeMap, first, second));
    }
}