package ru.job4j.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс описывает слой работы системы с базой данных.
 * Инициализация подключения реализована за счет паттерна
 * Синглтон.
 *
 * @author Pyotr Kukharenka
 * @since 21.02.2018
 */

public class UserStore implements Store {
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
    private final Connection connection;

    /**
     * Приватный конструктор для инициализации подключения
     * к базе данных
     */
    private UserStore() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    settings.property("url"),
                    settings.property("name"),
                    settings.property("password")
            );
        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Соединение с базой данных не установлено.");
        }
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
    public Collection<Users> getAll() {
        final List<Users> users = new ArrayList<>(100);
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                users.add(new Users(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        LocalDate.parse(rs.getString("create_date"))
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
    @Override
    public int add(Users user) {
        try (PreparedStatement pst = connection.prepareStatement(
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
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Невозможно создать пользователя");
    }

    /**
     * Возвращает true, если данные пользователя отредактированы.
     *
     * @param user - отредактированный пользовател.
     * @return - true, если данные пользователя отредактированы.
     */
    @Override
    public boolean update(Users user) {
        try (PreparedStatement pst = connection.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE id=?")) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setInt(4, user.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Пользователя с таким ID не существует");
    }

    /**
     * Возвращает true, если пользователь удален.
     *
     * @param id - id удаляемого пользователя
     * @return - true, если пользователь удален.
     */
    @Override
    public boolean delete(int id) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Пользователя с таким ID не существует");
    }

    /**
     * Возвращает пользователя с требуемым id.
     * @param id - id искомого пользователя
     * @return - пользователя с требуемым id.
     */
    @Override
    public Users get(int id) {
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            pst.setInt(1, id);
            pst.executeQuery();
            ResultSet rs = pst.getResultSet();
            if (rs.next()) {
                return new Users(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("email"),
                        LocalDate.parse(rs.getString("create_date"))
                );
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new IllegalStateException("Пользователя с таким ID не существует.");
    }
}
