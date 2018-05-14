package ru.job4j.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dao.Impl.ModelDao;
import ru.job4j.model.Model;

import java.util.List;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 06.04.2018
 */

@Slf4j
public class ModelServiceImpl implements GenericService<Model, Long>, ModelService {

    private final ModelDao modelDao = new ModelDao();

    @Override
    public List<Model> findBrands(String name) {
        return name != null ? this.modelDao.findBrands(name) : this.modelDao.findBrands("");
    }

    @Override
    public List<Model> findModelsByBrand(Long id) {
        return this.modelDao.findModelsByBrand(id);
    }

    @Override
    public Model saveOrUpdate(Model instance) {
        return this.modelDao.save(instance);
    }

    @Override
    public void delete(Model instance) {
        this.modelDao.delete(instance);
    }

    @Override
    public List<Model> findAll() {
        return this.modelDao.findAll();
    }

    @Override
    public Model findById(Long id) {
        return this.modelDao.findById(id);
    }
}
