package ru.job4j.crud.service;

import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для обновления пользователя
 *
 * @author Pyotr Kukharenka
 * @since 25.02.2018
 */

public class UpdateServlet extends HttpServlet {

    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("update.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.update(new Users(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email")
        ));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
