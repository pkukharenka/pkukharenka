package ru.job4j.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для добавления пользователя
 *
 * @author Pyotr Kukharenka
 * @since 25.02.2018
 */

public class AddServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.add(
                new Users(req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
        resp.sendRedirect(String.format("%s/crud", req.getContextPath()));
    }
}
