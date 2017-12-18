package ru.job4j.generic.service;

import org.junit.Test;
import ru.job4j.generic.SimpleArray;
import ru.job4j.generic.models.Role;
import ru.job4j.generic.models.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тесты для проверки работы хранилища объектов.
 *
 * @author Pyotr Kukharenka
 * @since 18.12.2017
 */
public class BaseStoreTest {
    /**
     * Проверяем что при добавлении объекта мы можем его
     * найти в хранилище и id будут равны.
     */
    @Test
    public void whenAddUserThenFindUserHaveId() {
        SimpleArray<User> users = new SimpleArray<>(4);
        UserStore store = new UserStore(users);
        store.add(new User("123"));
        assertThat(store.findById("123").getId(), is("123"));
    }

    /**
     * Проверяем что замена объекта вернет true
     */
    @Test
    public void whenUpdateRoleThenNewRoleId() {
        SimpleArray<Role> roles = new SimpleArray<>(4);
        RoleStore store = new RoleStore(roles);
        store.add(new Role("123"));
        assertThat(store.replace("123", new Role("456")), is(true));
    }

    /**
     * Првоеряем что после удаления объекта, метод findById его не
     * найдет, а вернет Null.
     */
    @Test
    public void whenDeleteUserThenFindReturnNull() {
        SimpleArray<User> users = new SimpleArray<>(5);
        UserStore store = new UserStore(users);
        store.add(new User("1"));
        store.add(new User("2"));
        store.add(new User("3"));
        store.delete("2");
        assertNull(store.findById("2"));
    }
}