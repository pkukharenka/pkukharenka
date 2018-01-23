package ru.job4j.jdbc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик xml документов.
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */

public class MyHandler extends DefaultHandler {
    /**
     * Лист для хранения значений.
     */
    private List<Integer> list;

    /**
     * Конструктор для инициализации листа.
     */
    public MyHandler() {
        this.list = new ArrayList<>(1000000);
    }

    /**
     * Возвращает заполненный лист.
     *
     * @return - заполненный лист
     */
    public List<Integer> getList() {
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * Метод обработки элементов xml структуры.
     *
     * @param uri        - uri.
     * @param localName  - имя
     * @param qName      - имя тэга
     * @param attributes - атрибуты тэга.
     * @throws SAXException - что-то пошло не так.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getQName(i);
            int value = Integer.valueOf(attributes.getValue(i));
            if (name.equals("field")) {
                this.list.add(value);
            }
        }
        super.startElement(uri, localName, qName, attributes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
}
