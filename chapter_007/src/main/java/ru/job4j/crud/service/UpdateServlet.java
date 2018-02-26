package ru.job4j.crud.service;

import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.User;

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
        User user = this.store.get(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/views/update.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.update(new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email")
        ));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
