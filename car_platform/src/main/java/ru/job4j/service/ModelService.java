package ru.job4j.service;

import ru.job4j.model.Model;

import java.util.List;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 06.04.2018
 */
public interface ModelService {

    List<Model> findBrands(String name);

    List<Model> findModelsByBrand(Long id);
}
