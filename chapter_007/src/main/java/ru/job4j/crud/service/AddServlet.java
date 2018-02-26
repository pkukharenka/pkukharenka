package ru.job4j.crud.service;

import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для добавления пользователя
 *
 * @author Pyotr Kukharenka
 * @since 25.02.2018
 */

public class AddServlet extends HttpServlet {

    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.add(
                new User(req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
