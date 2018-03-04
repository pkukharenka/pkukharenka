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
import java.io.IOException;
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
    private final RoleStore roleStore = new RoleStore();
    private final Settings settings = Settings.getInstance();
    private final DbConnect connect = DbConnect.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(this.settings.property("page.login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        Optional<User> validUser = this.checkUser(login, password);
        if (validUser.isPresent()) {
            LOG.info("Success login by {}", validUser.get().getLogin());
            HttpSession session = req.getSession();
            session.setAttribute("owner", validUser.get());
            req.setAttribute("users", this.userStore.findAll());
            req.setAttribute("roles", this.roleStore.findAll());
            req.getRequestDispatcher(this.settings.property("page.main")).forward(req, resp);
        } else {
            LOG.info("Try auth with login - {}, password - {}", login, password);
            req.setAttribute("error", "Invalid login or password");
            req.getRequestDispatcher(this.settings.property("page.login")).forward(req, resp);
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
