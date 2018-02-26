package ru.job4j.crud;

import org.junit.Test;
import ru.job4j.crud.model.User;
import ru.job4j.crud.dao.UserStore;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void addUser() {
        UserStore store = UserStore.getInstance();
        User user = new User("ivan", "iivanov", "ivanov@mail.ru");
        final int ids = store.add(user);
        assertThat(store.get(ids).getName(), is(user.getName()));
    }

    @Test
    public void updateUser() {
        UserStore store = UserStore.getInstance();
        final int ids = store.add(new User("artem", "artmiy2007", "artem@mail.ru"));
        User user = store.get(ids);
        user.setName("petr");
        store.update(user);
        assertThat(store.get(ids).getName(), is("petr"));
    }

    @Test
    public void deleteUser() {
        UserStore store = UserStore.getInstance();
        final int ids = store.add(new User("vitaliy", "vitaliy1989", "vit@mail.ru"));
        assertThat(store.delete(ids), is(ids));

    }

}