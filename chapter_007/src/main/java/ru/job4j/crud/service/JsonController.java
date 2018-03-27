package ru.job4j.crud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.Settings;
import ru.job4j.crud.dao.CityStore;
import ru.job4j.crud.dao.CountryStore;
import ru.job4j.crud.dao.RoleStore;
import ru.job4j.crud.dao.UserStore;
import ru.job4j.crud.model.City;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Контроллер главной страницы
 *
 * @author Pyotr Kukharenka
 * @since 07.03.2018
 */

@WebServlet("/json")
public class JsonController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(JsonController.class);
    private final ConcurrentHashMap<String, Function<String, String>> dispatch = new ConcurrentHashMap<>();
    private final UserStore userStore = new UserStore();
    private final CityStore cityStore = new CityStore();
    private final RoleStore roleStore = new RoleStore();
    private final CountryStore countryStore = new CountryStore();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Settings settings = Settings.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter pw = new PrintWriter(resp.getOutputStream());
             InputStream is = req.getServletContext().getResourceAsStream(this.settings.property("page.main"));
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            br.lines().forEach(pw::println);
            pw.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        LOG.info(req.getParameter("command"));
        final String result = this.initDispatch(req).findCommand(req.getParameter("command"));
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"))) {
            pw.print(result);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonController initDispatch(HttpServletRequest req) {
        this.dispatch.put("fillUpdateForm", this.fillUpdateForm(req));
        this.dispatch.put("fillCity", this.fillCity(req));
        this.dispatch.put("fillCountry", this.fillCountry());
        this.dispatch.put("fillRole", this.fillRole());
        this.dispatch.put("save", this.save(req));
        this.dispatch.put("add", this.add(req));
        this.dispatch.put("delete", this.delUser(req));
        this.dispatch.put("showAll", this.showAll());
        this.dispatch.put("logout", this.logout(req));
        this.dispatch.put("getUser", this.getUser(req));
        return this;
    }

    private String findCommand(String key) {
        return this.dispatch.get(key).apply(key);
    }


    private Function<String, String> fillCity(HttpServletRequest req) {
        return json -> {
            String result = "";
            List<City> cities = this.cityStore.findCitiesByCountryId(Integer.parseInt(req.getParameter("id")));
            try {
                result = this.mapper.writeValueAsString(cities);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage(), e);
            }
            return result;
        };
    }

    private Function<String, String> fillCountry() {
        return json -> {
            String result = "";
            List<Country> countries = this.countryStore.findAll();
            try {
                result = this.mapper.writeValueAsString(countries);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage(), e);
            }
            return result;
        };
    }

    private Function<String, String> fillRole() {
        return json -> {
            String result = "";
            List<Role> roles = this.roleStore.findAll();
            try {
                result = this.mapper.writeValueAsString(roles);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage(), e);
            }
            return result;
        };
    }

    private Function<String, String> fillUpdateForm(HttpServletRequest req) {
        return json -> {
            String result = "";
            final User user = this.userStore.get(Integer.parseInt(req.getParameter("id")));
            try {
                result = mapper.writeValueAsString(user);
                LOG.info(result);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage(), e);
            }

            return result;
        };
    }

    private Function<String, String> save(HttpServletRequest req) {
        return json -> {
            final User user = this.fillUserFields(req);
            user.setId(Integer.parseInt(req.getParameter("id")));
            this.userStore.update(user);
            return this.getOwnerRole(req);
        };
    }

    private Function<String, String> add(HttpServletRequest req) {
        return page -> {
            final User user = this.fillUserFields(req);
            this.userStore.add(user);
            return this.getOwnerRole(req);
        };
    }

    private User fillUserFields(HttpServletRequest req) {
        final User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setRole(new Role(Integer.parseInt(req.getParameter("role"))));
        user.setCountry(new Country(Integer.parseInt(req.getParameter("country"))));
        user.setCity(new City(Integer.parseInt(req.getParameter("city"))));
        return user;
    }

    private String getOwnerRole(HttpServletRequest req) {
        HttpSession session = req.getSession();
        final User owner = (User) session.getAttribute("owner");
        return owner.getRole().getType();
    }

    private Function<String, String> delUser(HttpServletRequest req) {
        return page -> {
            this.userStore.delete(Integer.parseInt(req.getParameter("id")));
            return "";
        };
    }

    private Function<String, String> showAll() {
        return json -> {
            String result = "";
            final List<User> users = this.userStore.findAll();
            try {
                result = mapper.writeValueAsString(users);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage(), e);
            }

            return result;
        };
    }

    private Function<String, String> logout(HttpServletRequest req) {
        return page -> {
            HttpSession session = req.getSession();
            session.invalidate();
            return "";
        };
    }

    private Function<String, String> getUser(HttpServletRequest req) {
        return page -> {
            String result = "";
            HttpSession session = req.getSession();
            final User user = (User) session.getAttribute("owner");
            try {
                result = this.mapper.writeValueAsString(user);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage(), e);
            }
            return result;
        };
    }
}
