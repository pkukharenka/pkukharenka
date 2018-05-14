package ru.job4j.dao.Impl;

import ru.job4j.dao.GenericDao;
import ru.job4j.model.User;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 04.04.2018
 */

public class UserDao extends GenericDao<User, Long> {

    protected UserDao() {
        super(User.class);
    }
}
