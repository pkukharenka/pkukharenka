package ru.job4j.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет обработки главной страницы
 *
 * @author Pyotr Kukharenka
 * @since 26.02.2018
 */

public class UserServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.store.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        this.store.closeData();
        super.destroy();
    }
}
