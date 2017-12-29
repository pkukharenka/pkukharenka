package ru.job4j.test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Парсер для обработки xml документа. Расширяет класс
 * DefaultHandler.
 *
 * @author Pyotr Kukharenka
 * @since 29.12.2017
 */

public class SaxParser extends DefaultHandler {
    /**
     * Структура для хранения обработанных заявок
     */
    private HashMap<Integer, Order> orders = new HashMap<>();

    /**
     * Возвращает полученную структуру с заявками.
     *
     * @return - структуру с заявками.
     */
    public HashMap<Integer, Order> getOrders() {
        return this.orders;
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
     * Метод обработки элементов xml документа. Проходим по
     * каждому атрибуту тэга и в случае соответствия тому
     * или иному полю класса Order устанавливаем значения.
     * В зависимости от типа тэга производим либо запись в
     * структуру хранения, либо удаления по id (ключу).
     *
     * @param uri        - uri.
     * @param localName  - local name.
     * @param qName      - имя тэга
     * @param attributes - атрибуты тэга
     * @throws SAXException - что-то пошло не так.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Order order = new Order();
        for (int index = 0; index < attributes.getLength(); index++) {
            String value = attributes.getValue(index);
            String name = attributes.getQName(index);
            if (name.equals("book")) {
                order.setBook(value);
            } else if (name.equals("operation")) {
                order.setOperation(value);
            } else if (name.equals("price")) {
                order.setPrice(Double.parseDouble(value));
            } else if (name.equals("volume")) {
                order.setVolume(Integer.parseInt(value));
            } else if (name.equals("orderId")) {
                order.setId(Integer.parseInt(value));
            }
        }
        if (qName.equals("AddOrder")) {
            this.orders.put(order.getId(), order);
        } else {
            this.orders.remove(order.getId());
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

