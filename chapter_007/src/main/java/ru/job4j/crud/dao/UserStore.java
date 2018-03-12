package ru.job4j.crud.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.City;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.util.DbConnect;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает слой работы системы с базой данных.
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class UserStore implements Store<User> {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private final DbConnect dataSource = DbConnect.getInstance();

    /**
     * Возвращает список всех пользователей.
     *
     * @return - список всех пользователей.
     */
    @Override
    public List<User> findAll() {
        final List<User> users = new ArrayList<>();
        try (Connection cn = this.dataSource.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * "
                     + "FROM users AS u LEFT JOIN role AS r ON u.role_id = r.id "
                     + "  LEFT JOIN country AS co ON u.coutry_id = co.id "
                     + "  LEFT JOIN city AS ci ON u.city_id = ci.id "
                     + "ORDER BY u.id")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        LocalDate.parse(rs.getString("create_date")),
                        new Role(rs.getInt("role_id"), rs.getString("type")),
                        new Country(rs.getInt("country_id"), rs.getString("country_name")),
                        new City(rs.getInt("city_id"), rs.getString("city_name"))
                ));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    /**
     * Добавляет нового пользователя и возвращает его уникальный
     * id.
     *
     * @param user - новый пользователю
     * @return - id пользователя.
     */
    public int add(User user) {
        int newUserId = 0;
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement(
                     "INSERT INTO users (name, login, email, create_date, password, role_id, coutry_id, city_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setDate(4, Date.valueOf(LocalDate.now()));
            pst.setString(5, user.getPassword());
            pst.setInt(6, user.getRole().getId());
            pst.setInt(7, user.getCountry().getId());
            pst.setInt(8, user.getCity().getId());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    newUserId = rs.getInt("id");
                }
            }
            LOG.info("Пользователь с id = {} добавлен", newUserId);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return newUserId;
    }

    /**
     * Возвращает true, если данные пользователя отредактированы.
     *
     * @param user - отредактированный пользовател.
     * @return - true, если данные пользователя отредактированы.
     */
    public int update(User user) {
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement("UPDATE users SET name=?, login=?, email=?, "
                     + "password=?, role_id=?, coutry_id=?, city_id=? WHERE id=?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setInt(5, user.getRole().getId());
            pst.setInt(6, user.getCountry().getId());
            pst.setInt(7, user.getCity().getId());
            pst.setInt(8, user.getId());
            pst.executeUpdate();
            LOG.info("Пользователь с id = {} обновлен", user.getId());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user.getId();
    }

    /**
     * Возвращает true, если пользователь удален.
     *
     * @param id - id удаляемого пользователя
     * @return - true, если пользователь удален.
     */
    public int delete(int id) {
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement("DELETE FROM users WHERE id=?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
            LOG.info("Пользователь с id = {} удален", id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return id;
    }

    /**
     * Возвращает пользователя с требуемым id.
     *
     * @param id - id искомого пользователя
     * @return - пользователя с требуемым id.
     */
    public User get(int id) {
        User user = null;
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement("SELECT *"
                     + "FROM users AS u LEFT JOIN role AS r ON u.role_id = r.id"
                     + "  LEFT JOIN country AS co ON u.coutry_id = co.id"
                     + "  LEFT JOIN city AS ci ON u.city_id = ci.id "
                     + "WHERE u.id = ?")) {
            pst.setInt(1, id);
            pst.executeQuery();
            try (ResultSet rs = pst.getResultSet()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("email"),
                            LocalDate.parse(rs.getString("create_date")),
                            new Role(rs.getInt("role_id"), rs.getString("type")),
                            new Country(rs.getInt("country_id"), rs.getString("country_name")),
                            new City(rs.getInt("city_id"), rs.getString("city_name"))
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

}
