package ru.job4j.todolist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.model.Item;

import javax.persistence.Query;
import java.util.List;

/**
 * Дао слой с основными методами для взаимодействия
 * с сущностью
 *
 * @author Pyotr Kukharenka
 * @since 21.03.2018
 */

public class ItemDao {

    private final SessionFactory sessionFactory;

    public ItemDao() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public int save(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        final int id = (int) session.save(item);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Item item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    public List<Item> findItemsByDone() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item where done=false").getResultList();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    public Item getItem(int id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where id = :id");
        query.setParameter("id", id);
        final Item item = (Item) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete from Item").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
