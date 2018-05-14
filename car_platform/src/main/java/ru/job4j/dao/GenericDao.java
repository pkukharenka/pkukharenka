package ru.job4j.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.util.HibernateUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * Абстракция уровня доступа к данным
 *
 * @author Pyotr Kukharenka
 * @since 31.03.2018
 */

@Slf4j
public abstract class GenericDao<T, ID extends Serializable> {

    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = HibernateUtil.getSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (Exception e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public T save(T instance) {
        return tx(session -> {
            session.saveOrUpdate(instance);
            return instance;
        });
    }

    public T delete(T instance) {
        return tx(session -> {
            session.delete(instance);
            return instance;
        });
    }


    public List<T> findAll() {
        return tx(session -> {
            final CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(entityClass);
            return session.createQuery(query).getResultList();
        });
    }

    public T findById(ID id) {
        return tx(session -> session.get(entityClass, id));
    }
}
