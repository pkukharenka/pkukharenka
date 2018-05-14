package ru.job4j.dao.Impl;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.job4j.dao.GenericDao;
import ru.job4j.model.Model;
import ru.job4j.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Set;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 04.04.2018
 */

@Slf4j
public class ModelDao extends GenericDao<Model, Long> {

    public ModelDao() {
        super(Model.class);
    }

    @SuppressWarnings(value = "unchecked")
    public List<Model> findBrands(String name) {
        List<Model> brands = null;
        try (Session session = HibernateUtil.getSession()) {
            brands = session.createQuery("from Model where parent=null and upper(modelName) like upper(:name)")
                    .setParameter("name", "%" + name + "%").getResultList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return brands;
    }

    @SuppressWarnings(value = "unchecked")
    public List<Model> findModelsByBrand(Long id) {
        List<Model> models = null;
        try (Session session = HibernateUtil.getSession()) {
            models = session.createQuery("from Model where parent.id=:id").setParameter("id", id).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return models;
    }
}
