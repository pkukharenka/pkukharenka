package ru.job4j.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.dao.UserStore;

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
        pw.print("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/add' method='post'>" +
                "    Name: <input type='text' name='name'/>\n" +
                "    Login: <input type='text' name='login'/>\n" +
                "    Email: <input type='text' name='email'/>\n" +
                "    <input type='submit'/>" +
                "</form>" +
                this.toText(req) +
                "</body>" +
                "</html>");
        pw.flush();
        pw.close();
    }

    /**
     * Возвращает данные из базы ввиде таблицы html
     *
     * @param req - запрос пользователя
     * @return - табоицу с данными
     */
    private String toText(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<tr>");
        sb.append("<th>ID</th>");
        sb.append("<th>name</th>");
        sb.append("<th>Login</th>");
        sb.append("<th>Email</th>");
        sb.append("<th>Create Date</th>");
        sb.append("<th>Delete</th>");
        sb.append("<th>Update</th>");
        sb.append("</tr>");
        this.store.getAll().forEach(user -> {
            sb.append("<tr>");
            sb.append(String.format("<td>%s</td>", user.getId()));
            sb.append(String.format("<td>%s</td>", user.getName()));
            sb.append(String.format("<td>%s</td>", user.getLogin()));
            sb.append(String.format("<td>%s</td>", user.getEmail()));
            sb.append(String.format("<td>%s</td>", user.getCreateDate()));
            sb.append(String.format("<td><a href='%s/del?id=%s'>Delete</a></td>", req.getContextPath(), user.getId()));
            sb.append(String.format("<td><a href='%s/update?id=%s'>Update</a></td>", req.getContextPath(), user.getId()));
            sb.append("</tr>");
        });
        sb.append("</table>");

        return sb.toString();
    }

}
