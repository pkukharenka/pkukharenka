package ru.job4j.dao.impl;

import ru.job4j.dao.GenericDao;
import ru.job4j.model.Role;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 04.04.2018
 */

public class RoleDao extends GenericDao<Role, Long> {

    protected RoleDao() {
        super(Role.class);
    }
}
