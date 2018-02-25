package ru.job4j.crud.service;

import ru.job4j.crud.dao.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для удаления пользователя
 *
 * @author Pyotr Kukharenka
 * @since 25.02.2018
 */

public class DelServlet extends HttpServlet {

    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
