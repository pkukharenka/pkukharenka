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
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private final UserStore store = new UserStore();
    private final Settings settings = Settings.getInstance();

    @Mock
    private final HttpServletRequest req = mock(HttpServletRequest.class);
    @Mock
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    @Mock
    private final RequestDispatcher rd = mock(RequestDispatcher.class);
    @Mock
    private final HttpSession session = mock(HttpSession.class);

    @Test
    public void whenLoginWithValidDataThenMoveToMainPage() throws ServletException, IOException {
        final int id = this.store.add(new User("petr", "petr", "123", "petr@mail.ru", new Role(2, "user")));

        when(req.getParameter("login")).thenReturn("petr");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher(this.settings.property("page.main"))).thenReturn(rd);

        new LoginController().doPost(this.req, this.resp);

        this.store.delete(id);
    }

    @Test
    public void whenLoginWithInvalidThenStayOnLoginPage() throws ServletException, IOException {
        when(req.getParameter("login")).thenReturn("invalidUser");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher(this.settings.property("page.login"))).thenReturn(rd);

        new LoginController().doPost(this.req, this.resp);
    }
}