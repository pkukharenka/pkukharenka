package ru.job4j.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обработки главной страницы
 *
 * @author Pyotr Kukharenka
 * @since 26.02.2018
 */

public class UserServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    private final Command command = new Command();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void destroy() {
        this.command.closeData();
        super.destroy();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String page = this.command.initDispatch(req).findCommand(req.getParameter("command"));
        if (page != null) {
            try {
               req.getRequestDispatcher(page).forward(req, resp);
            } catch (ServletException | IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }


}
