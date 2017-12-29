package ru.job4j.test;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Программа для отображения спроса и предложений по книгам
 *
 * @author Pyotr Kukharenka
 * @since 29.12.2017
 */

public class Analyse {
    /**
     * Структура для хранения предложенной цены и имеющегося
     * количества по каждой книге.
     */
    private HashMap<String, TreeMap<Integer, Integer>> bid = new HashMap<>();
    /**
     * Структура для хранения спроса на имеющееся количество книг и цену
     * приобретения.
     */
    private HashMap<String, TreeMap<Integer, Integer>> ask = new HashMap<>();

    /**
     * Возвращает структуру со всеми заявками по предложениям и спросу
     * книг. Информация парсится из xml файла. В качестве обработчика
     * используется SAX парсер.
     *
     * @param fileName - имя файла для обработки.
     * @return - структуру со всеми заявками
     * @see SaxParser
     */
    private HashMap<Integer, Order> parseFile(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);
        SAXParser parser;
        InputStream is;
        SaxParser prs = new SaxParser();
        try {
            is = new FileInputStream(fileName);
            parser = factory.newSAXParser();
            parser.parse(is, prs);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return prs.getOrders();
    }

    /**
     * Метод обрабатывает полученную структуру из метода parseFile и
     * преобразует ее в две структуры, каждая из которых отвечает
     * за спрос или предложение.
     * bid - предложения.
     * ask - спрос.
     * Каждая из эти структур будет хранить данные ввиде:
     * наименование книги - (цена книги - количество книг).
     * При этом структура bid сортируется в порядке убывания цены, а
     * структура ask в порядке возрастания. При каждом добавлении элемента
     * в bid/ask мы проверяем не содержиться ли книга с такой ценой в
     * противоположной структуре
     *
     * @param fileName - имя файла для обработки.
     */
    public void fillMaps(String fileName) {
        HashMap<Integer, Order> orders = this.parseFile(fileName);
        for (Order order : orders.values()) {
            if (order.getOperation().equals("BUY")) {
                this.add(this.bid, this.ask, order, (o1, o2) -> -(o1 - o2));
            } else {
                this.add(this.ask, this.bid, order, (o1, o2) -> o1 - o2);
            }
        }
        this.toConsole();
    }

    /**
     * Метод проверяет наличие в структурах bid/ask очередной для добавления
     * книги. При этом существует два случая:
     * 1. Если книга есть в обеих структурах, мы проверяем стоимость и количество.
     * 2. Во всех оствльных случаях мы вначале создаем для этой книги структуру
     * для хранения цен и значений, а затем проверям стоимость и количество.
     *
     * @param input    - структура в которую планируется добавляться значение.
     * @param opposite - противоположная структура для сравнения наличия
     *                 цен и агрегации количества.
     * @param order    - обрабатываемая заявка.
     * @param cpr      - компаратор.
     */
    private void add(HashMap<String, TreeMap<Integer, Integer>> input, HashMap<String, TreeMap<Integer, Integer>> opposite,
                     Order order, Comparator<Integer> cpr) {
        final String book = order.getBook();
        if (opposite.containsKey(book) && input.containsKey(book)) {
            this.checkPrice(input.get(book), opposite.get(book), order);
        } else {
            input.putIfAbsent(book, new TreeMap<>(cpr));
            this.checkPrice(input.get(book), opposite.get(book), order);
        }
    }

    /**
     * Метод проверяет в структурах bid/ask цены и количества книг. Существует
     * 2 основных ситуации:
     * 1. opposite содержит такую стоимость как у очередной
     * заявки -> 3 варианта развития событий:
     * 1.1. Очередное количество больше того, которое есть в opposite ->
     * добавляем в input за вычетом того что есть в opposite -> удаляем
     * значение в opposite.
     * 1.2. Очередное количество равно тому, которое есть в opposite ->
     * удаляем в opposite, в input не добавляем.
     * 1.3. Очередное количество меньше того, которое есть в opposite ->
     * уменьшаем в opposite на это количество, в input не добавляем.
     * 2.opposite пустая или не содержит такую стоимость -> добавляем в input.
     *
     * @param input    - структура в которую планируется добавляться значение.
     * @param opposite - противоположная структура для сравнения наличия
     *                 цен и агрегации количества.
     * @param order    - обрабатываемая заявка.
     */
    public void checkPrice(TreeMap<Integer, Integer> input, TreeMap<Integer, Integer> opposite, Order order) {
        int price = (int) (order.getPrice() * 100);
        int volume = order.getVolume();
        if (opposite != null && opposite.containsKey(price)) {
            if (volume > opposite.get(price)) {
                input.computeIfPresent(price, (k, v) -> v + order.getVolume() - opposite.get(price));
                input.computeIfAbsent(price, v -> order.getVolume() - opposite.get(price));
                opposite.remove(price);
            } else if (volume == opposite.get(price)) {
                opposite.remove(price);
            } else if (volume < opposite.get(price)) {
                opposite.computeIfPresent(price, (k, v) -> v - order.getVolume());
            }
        } else {
            input.computeIfPresent(price, (k, v) -> v + order.getVolume());
            input.computeIfAbsent(price, v -> order.getVolume());
        }
    }

    /**
     * Обработка для вывода на консоль одинаковых книг в bid и ask, либо
     * если одна из структур не имеет такой книги выводятся цены только
     * по одной структуре.
     */
    private void toConsole() {
        Iterator<String> bidIt = this.bid.keySet().iterator();
        Iterator<String> askIt = this.bid.keySet().iterator();
        while (bidIt.hasNext() || askIt.hasNext()) {
            if ((bidIt.hasNext() && askIt.hasNext()) || (bidIt.hasNext() && !askIt.hasNext())) {
                String s = bidIt.next();
                this.printPrice(s);
            } else if (!bidIt.hasNext() && askIt.hasNext()) {
                String s = askIt.next();
                this.printPrice(s);
            }
        }
    }

    /**
     * Метод выводит на консоль данные из двух структур bid и ask ввиде:
     * Volume@Price (bid) -- Volume@Price (ask).
     *
     * @param book - книга для обработки.
     */
    public void printPrice(String book) {
        System.out.printf("Order book: %s\n BID       ASK\nVolume@Price  --  Volume@Price\n", book);
        Iterator<Integer> itBid = this.bid.get(book).keySet().iterator();
        Iterator<Integer> itAsk = this.ask.get(book).keySet().iterator();
        while (itBid.hasNext() || itAsk.hasNext()) {
            if (itBid.hasNext() && itAsk.hasNext()) {
                int bidPrice = itBid.next();
                double bPrice = (double) bidPrice / 100;
                int askPrice = itAsk.next();
                double aPrice = (double) askPrice / 100;
                System.out.printf("%s@%s   --   %s@%s\n", this.bid.get(book).get(bidPrice), bPrice,
                        this.ask.get(book).get(askPrice), aPrice);
            } else if (!itBid.hasNext() && itAsk.hasNext()) {
                int askPrice = itAsk.next();
                double aPrice = (double) askPrice / 100;
                System.out.printf("-------   --   %s@%s\n", this.ask.get(book).get(askPrice), aPrice);
            } else if (itBid.hasNext() && !itAsk.hasNext()) {
                int bidPrice = itBid.next();
                double bPrice = (double) bidPrice / 100;
                System.out.printf("%s@%s   --   -------\n", this.bid.get(book).get(bidPrice), bPrice);
            }
        }
    }

    /**
     * Метод для запуска программы.
     *
     * @param args - входящие параметры.
     */
    public static void main(String[] args) {
        Analyse a = new Analyse();
        a.fillMaps("E:\\orders.xml");
    }
}





