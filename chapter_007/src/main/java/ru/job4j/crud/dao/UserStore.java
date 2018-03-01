package ru.job4j.crud.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.Settings;
import ru.job4j.crud.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывает слой работы системы с базой данных.
 * Инициализация подключения реализована за счет паттерна
 * Синглтон.
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class UserStore implements Store<User> {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);
    /**
     * Инстанс класса.
     */
    private static final UserStore INSTANCE = new UserStore();
    /**
     * Соединене с базой данных
     */
    private final BasicDataSource dataSource;

    /**
     * Приватный конструктор для инициализации подключения
     * к базе данных
     */
    private UserStore() {
        final Settings settings = Settings.getInstance();
        this.dataSource = new BasicDataSource();
        this.dataSource.setUrl(settings.property("url"));
        this.dataSource.setUsername(settings.property("name"));
        this.dataSource.setPassword(settings.property("password"));
        this.dataSource.setDriverClassName(settings.property("driver"));
    }

    /**
     * Инициализация инстанса.
     *
     * @return - инстанс класса.
     */
    public static UserStore getInstance() {
        return INSTANCE;
    }

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
             ResultSet rs = st.executeQuery("SELECT * FROM users ORDER BY id")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        LocalDate.parse(rs.getString("create_date"))
                ));
            }
            LOG.info("Найдено пользователей - {}", users.size());
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
    @Override
    public int add(User user) {
        int newUserId = 0;
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement(
                     "INSERT INTO users (name, login, email, create_date) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setDate(4, Date.valueOf(LocalDate.now()));
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
    @Override
    public int update(User user) {
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE id=?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setInt(4, user.getId());
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
    @Override
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
    @Override
    public User get(int id) {
        User user = null;
        try (Connection cn = this.dataSource.getConnection();
             PreparedStatement pst = cn.prepareStatement("SELECT * FROM users WHERE id=?")) {
            pst.setInt(1, id);
            pst.executeQuery();
            try (ResultSet rs = pst.getResultSet()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("login"),
                            rs.getString("email"),
                            LocalDate.parse(rs.getString("create_date"))
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public void closeData() {
        try {
            this.dataSource.close();
            LOG.info("Датасорс закрыт - {}", this.dataSource.isClosed());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
