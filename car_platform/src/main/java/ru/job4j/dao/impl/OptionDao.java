package ru.job4j.dao.impl;

import ru.job4j.dao.GenericDao;
import ru.job4j.model.Option;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 04.04.2018
 */

public class OptionDao extends GenericDao<Option, Long> {

    protected OptionDao() {
        super(Option.class);
    }
}
