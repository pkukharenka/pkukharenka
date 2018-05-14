package ru.job4j.dao.Impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import ru.job4j.model.Model;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ModelDaoTest {

    private final ModelDao modelDao = new ModelDao();

    @Test
    public void whenFindByBrandsThenCountIs1() {
        List<Model> brands = this.modelDao.findBrands("ud"); //only Audi
        assertEquals(brands.size(), 1);
    }

    @Test
    public void whenFindModelsByBrandId2Then() {
        List<Model> models = this.modelDao.findModelsByBrand(2L);
        assertEquals(models.size(), 17);
    }

}