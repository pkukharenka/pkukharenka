package ru.job4j.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.job4j.model.Car;
import ru.job4j.model.Model;
import ru.job4j.model.User;

import static org.junit.Assert.*;

@Slf4j
public class CarDaoTest {

    private final CarDao dao = new CarDao();

    @Test
    public void whenSaveThenFirstCarIdIs1() {
        Car car = new Car();
        car.setModel(new Model(5L));
        car.setHolder(new User(1L));
        assertEquals(this.dao.save(car), 1L);

    }

    @Test
    public void whenUpdateThenDeletedFlagIsTrue() {
        Car car = new Car();
        car.setModel(new Model(5L));
        car.setHolder(new User(1L));
        Long id = this.dao.save(car);
        Car newCar = this.dao.findById(id);
        newCar.setDeleted(true);
        this.dao.save(newCar);
        assertTrue(this.dao.findById(id).isDeleted());
    }
}