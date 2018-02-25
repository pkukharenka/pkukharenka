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
 * Сервлет для обновления пользователя
 *
 * @author Pyotr Kukharenka
 * @since 25.02.2018
 */

public class UpdateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Users user = this.store.get(Integer.parseInt(req.getParameter("id")));
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.print("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Update</title>" +
                "</head>" +
                "<body>" +
                "<h1>Update user</h1>" +
                "<form action='" + req.getContextPath() + "/update' method='post'>" +
                "    ID: <input type='text' name='id' value='" + user.getId() + "' hidden/>" +
                "    Name: <input type='text' name='name' value='" + user.getName() + "' />" +
                "    Login: <input type='text' name='login' value='" + user.getLogin() + "' />" +
                "    Email: <input type='text' name='email' value='" + user.getEmail() + "' />" +
                "    <input type='submit'/>" +
                "</form>" +
                "</body>" +
                "</html>");
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.store.update(new Users(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email")
        ));
        resp.sendRedirect(String.format("%s/crud", req.getContextPath()));
    }
}
