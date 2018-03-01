package ru.job4j.crud.service;

import ru.job4j.crud.Settings;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Класс реализующий функционал запросов пользователей
 *
 * @author Pyotr Kukharenka
 * @since 28.02.2018
 */

public class Command {

    private final ConcurrentHashMap<String, Function<String, String>> dispatch = new ConcurrentHashMap<>();
    private final UserStore store = UserStore.getInstance();
    private final Settings settings = Settings.getInstance();

    public Command initDispatch(HttpServletRequest req) {
        this.dispatch.put("add", this.addUser(req));
        this.dispatch.put("del", this.delUser(req));
        this.dispatch.put("showAll", this.showUsers(req));
        this.dispatch.put("fillUpdate", this.fillUpdate(req));
        this.dispatch.put("update", this.updateUser(req));
        return this;
    }

    public String findCommand(String key) {
        return this.dispatch.get(key).apply(key);
    }

    private Function<String, String> addUser(HttpServletRequest req) {
        return page -> {
            this.store.add(new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email")
            ));
            this.fillUsers(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> delUser(HttpServletRequest req) {
        return page -> {
            this.store.delete(Integer.parseInt(req.getParameter("id")));
            this.fillUsers(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> fillUpdate(HttpServletRequest req) {
        return page -> {
            User user = this.store.get(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
            this.fillUsers(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> updateUser(HttpServletRequest req) {
        return page -> {
            this.store.update(new User(
                    Integer.parseInt(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email")
            ));
            this.fillUsers(req);
            return this.settings.property("page.main");
        };
    }

    private Function<String, String> showUsers(HttpServletRequest req) {
        return page -> {
            this.fillUsers(req);
            return this.settings.property("page.main");
        };
    }

    private void fillUsers(HttpServletRequest req) {
        List<User> users = this.store.findAll();
        req.setAttribute("users", users);
    }

    public void closeData() {
        this.store.closeData();
    }
}
