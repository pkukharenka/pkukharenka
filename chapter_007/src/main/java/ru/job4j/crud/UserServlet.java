package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Основной сервлет программы.
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class UserServlet extends HttpServlet {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    /**
     * Объект для работы с базой данных
     */
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        this.store.getAll().forEach(users -> {
            pw.println(users.toString());
            pw.flush();
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.add(
                new Users(req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
        this.doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.update(
                new Users(Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
        this.doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.delete(Integer.parseInt(req.getParameter("id")));
        this.doGet(req, resp);
    }
}
