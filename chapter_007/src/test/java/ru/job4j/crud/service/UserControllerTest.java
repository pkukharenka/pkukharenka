package ru.job4j.crud.service;

import org.junit.Test;
import org.mockito.Mock;
import ru.job4j.crud.Settings;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private final UserStore store = new UserStore();
    private final Settings settings = Settings.getInstance();

    @Mock
    private final HttpServletRequest req = mock(HttpServletRequest.class);

    @Mock
    private final HttpServletResponse resp = mock(HttpServletResponse.class);

    @Mock
    private final RequestDispatcher rd = mock(RequestDispatcher.class);


    @Test
    public void whenAddUser() throws ServletException, IOException {
        when(this.req.getParameter("command")).thenReturn("add");
        when(this.req.getParameter("name")).thenReturn("Andrey");
        when(this.req.getParameter("login")).thenReturn("andr");
        when(this.req.getParameter("password")).thenReturn("123");
        when(this.req.getParameter("email")).thenReturn("andr@mail.ru");
        when(this.req.getParameter("role")).thenReturn("2");
        when(this.req.getRequestDispatcher(this.settings.property("page.main"))).thenReturn(this.rd);

        new UserController().doPost(this.req, this.resp);

        final List<User> users = this.store.findAll();
        final User testUser = users.get(users.size() - 1);
        assertThat(testUser.getName(), is("Andrey"));
        this.store.delete(testUser.getId());
    }

    @Test
    public void whenUpdateUser() throws ServletException, IOException {
        final int id = this.store.add(new User("petr", "petr", "123", "petr@mail.ru", new Role(2, "user")));
        when(this.req.getParameter("command")).thenReturn("update");
        when(this.req.getParameter("id")).thenReturn(String.valueOf(id));
        when(this.req.getParameter("name")).thenReturn("Andrey");
        when(this.req.getParameter("login")).thenReturn("andr");
        when(this.req.getParameter("password")).thenReturn("123");
        when(this.req.getParameter("email")).thenReturn("andr@mail.ru");
        when(this.req.getParameter("role")).thenReturn("2");
        when(this.req.getRequestDispatcher(this.settings.property("page.main"))).thenReturn(this.rd);

        new UserController().doPost(this.req, this.resp);

        User user = this.store.get(id);
        assertThat(user.getName(), is("Andrey"));
        this.store.delete(id);
    }

    @Test
    public void whenDeleteUser() throws ServletException, IOException {
        final int id = this.store.add(new User("petr", "petr", "123", "petr@mail.ru", new Role(2, "user")));
        when(this.req.getParameter("command")).thenReturn("del");
        when(this.req.getParameter("id")).thenReturn(String.valueOf(id));
        when(this.req.getRequestDispatcher(this.settings.property("page.main"))).thenReturn(this.rd);

        new UserController().doPost(this.req, this.resp);

        assertNull(this.store.get(id));
    }


}