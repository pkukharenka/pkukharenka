package ru.job4j.crud;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UsersStoreTest {

    @Test
    public void addUser() {
        UserStore store = UserStore.getInstance();
        Users user = new Users("ivan", "iivanov", "ivanov@mail.ru");
        final int ids = store.add(user);
        assertThat(store.get(ids).getName(), is(user.getName()));
    }

    @Test
    public void updateUser() {
        UserStore store = UserStore.getInstance();
        final int ids = store.add(new Users("artem", "artmiy2007", "artem@mail.ru"));
        Users user = store.get(ids);
        user.setName("petr");
        store.update(user);
        assertThat(store.get(ids).getName(), is("petr"));
    }

    @Test(expected = IllegalStateException.class)
    public void deleteUser() {
        UserStore store = UserStore.getInstance();
        final int ids = store.add(new Users("vitaliy", "vitaliy1989", "vit@mail.ru"));
        store.delete(ids);
        store.get(ids);
    }

}