package ru.job4j.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.model.Car;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Методы для записи и извлечения данных об
 * объявлениях о продаже авто.
 *
 * @author Pyotr Kukharenka
 * @since 31.03.2018
 */

@Transactional
@Slf4j
public class CarDao implements AbstractDao<Car> {

    private static SessionFactory factory;

    static {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public long save(Car car) {
        Session session = factory.openSession();
        session.saveOrUpdate(car);
        final long id = car.getId();
        session.close();
        return id;
    }

    @Override
    public void delete(Car car) {
    Session session = factory.openSession();
    session.delete(car);
    session.close();
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Car> findAll() {
        Session session = factory.openSession();
        List<Car> cars = session.createQuery("from Car").list();
        session.close();
        return cars;
    }

    @Override
    public Car findById(long id) {
        Session session = factory.openSession();
        Car car = session.get(Car.class, id);
        session.close();
        return car;
    }


}
