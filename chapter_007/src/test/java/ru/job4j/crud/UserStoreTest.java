package ru.job4j.crud;

import org.junit.Test;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;
import ru.job4j.crud.dao.UserStore;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void addUser() {
        UserStore store = new UserStore();
        User user = new User("ivan", "iivanov", "123", "ivanov@mail.ru", new Role(1, "admin"));
        final int ids = store.add(user);
        assertThat(store.get(ids).getName(), is(user.getName()));
    }

    @Test
    public void updateUser() {
        UserStore store = new UserStore();
        final int ids = store.add(new User("artem", "artmiy2007", "123", "artem@mail.ru", new Role(2, "user")));
        User user = store.get(ids);
        user.setName("petr");
        store.update(user);
        assertThat(store.get(ids).getName(), is("petr"));
    }

    @Test
    public void deleteUser() {
        UserStore store = new UserStore();
        final int ids = store.add(new User("vitaliy", "vitaliy1989", "123", "vit@mail.ru", new Role(2, "user")));
        assertThat(store.delete(ids), is(ids));

    }

}