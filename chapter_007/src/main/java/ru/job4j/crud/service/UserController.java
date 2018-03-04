package ru.job4j.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.Settings;
import ru.job4j.crud.util.DbConnect;
import ru.job4j.crud.dao.RoleStore;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Сервлет обработки главной страницы
 *
 * @author Pyotr Kukharenka
 * @since 26.02.2018
 */
@WebServlet("/crud")
public class UserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final ConcurrentHashMap<String, Function<String, String>> dispatch = new ConcurrentHashMap<>();
    private final UserStore userStore = new UserStore();
    private final RoleStore roleStore = new RoleStore();
    private final Settings settings = Settings.getInstance();
    private final DbConnect connect = DbConnect.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.fillData(req);
        req.getRequestDispatcher(this.settings.property("page.main")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    public void destroy() {
        this.connect.closeData();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String page = this.initDispatch(req).findCommand(req.getParameter("command"));
        if (page != null) {
            try {
                req.getRequestDispatcher(page).forward(req, resp);
            } catch (ServletException | IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private UserController initDispatch(HttpServletRequest req) {
        this.dispatch.put("add", this.addUser(req));
        this.dispatch.put("del", this.delUser(req));
        this.dispatch.put("showAll", this.showUsers(req));
        this.dispatch.put("fillUpdate", this.fillUpdate(req));
        this.dispatch.put("update", this.updateUser(req));
        this.dispatch.put("logout", this.logout(req));
        return this;
    }

    private String findCommand(String key) {
        return this.dispatch.get(key).apply(key);
    }

    private Function<String, String> addUser(HttpServletRequest req) {
        return page -> {
            this.userStore.add(new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password"),
                    req.getParameter("email"),
                    new Role(Integer.parseInt(req.getParameter("role")))
            ));
            this.fillData(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> delUser(HttpServletRequest req) {
        return page -> {
            this.userStore.delete(Integer.parseInt(req.getParameter("id")));
            this.fillData(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> fillUpdate(HttpServletRequest req) {
        return page -> {
            User user = this.userStore.get(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
            this.fillData(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> updateUser(HttpServletRequest req) {
        return page -> {
            this.userStore.update(new User(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password"),
                    req.getParameter("email"),
                    new Role(Integer.parseInt(req.getParameter("role")))
            ));
            this.fillData(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> showUsers(HttpServletRequest req) {
        return page -> {
            this.fillData(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> logout(HttpServletRequest req) {
        return page -> {
            HttpSession session = req.getSession();
            session.invalidate();
            return this.settings.property("page.login");
        };
    }

    private void fillData(HttpServletRequest req) {
        req.setAttribute("users", this.userStore.findAll());
        req.setAttribute("roles", this.roleStore.findAll());
    }

}
