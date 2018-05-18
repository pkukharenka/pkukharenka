package ru.job4j.dao.impl;

import ru.job4j.dao.GenericDao;
import ru.job4j.model.Car;

/**
 * Методы для записи и извлечения данных об
 * объявлениях о продаже авто.
 *
 * @author Pyotr Kukharenka
 * @since 31.03.2018
 */

public class CarDao extends GenericDao<Car, Long> {

    public CarDao() {
        super(Car.class);
    }
}
