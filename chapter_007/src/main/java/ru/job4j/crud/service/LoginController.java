package ru.job4j.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.Settings;
import ru.job4j.crud.util.DbConnect;
import ru.job4j.crud.dao.RoleStore;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Контроллер страницы авторизации пользователей
 *
 * @author Pyotr Kukharenka
 * @since 03.03.2018
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private final UserStore userStore = new UserStore();
    private final Settings settings = Settings.getInstance();
    private final DbConnect connect = DbConnect.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter pw = new PrintWriter(resp.getOutputStream());
             InputStream is = req.getServletContext().getResourceAsStream(this.settings.property("page.login"));
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            br.lines().forEach(pw::println);
            pw.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        String result;
        Optional<User> validUser = this.checkUser(login, password);
        if (validUser.isPresent()) {
            LOG.info("Success login by {}", validUser.get().getLogin());
            HttpSession session = req.getSession();
            session.setAttribute("owner", validUser.get());
            result = "OK";
        } else {
            LOG.info("Try auth with login - {}, password - {}", login, password);
            result = "ERROR";
        }
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"))) {
            pw.print(result);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        this.connect.closeData();
    }

    private Optional<User> checkUser(final String login, final String password) {
        return this.userStore.findAll().stream().
                filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password)).
                findAny();
    }

}
