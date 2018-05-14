package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.model.Item;

import java.sql.*;
import java.util.*;

/**
 * Класс трекер, который включает в себя основные инструменты
 * по работе с объектами типа Item.
 *
 * @author Pyotr Kukharenka
 * @since 28.01.2018
 */

public class Tracker {
    /**
     * Логгер
     */
    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);

    /**
     * Утилиты для работы с базой данных.
     */
    private Util util;

    /**
     * Инициализации утилитного класса.
     */
    public Tracker() {
        this.util = new Util();
    }

    /**
     * Метод добавления нового объекта.
     *
     * @param item - объект.
     * @return - объект.
     */
    public Item add(Item item) {
        String sql = "INSERT INTO item (item_name, description, create_date) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = this.util.getConnection().prepareStatement(sql);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setTimestamp(3, item.getCreated());
            ps.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            this.util.disconnect();
        }
        return item;
    }

    /**
     * Метод, позволяющий произвести изменение объекта.
     *
     * @param item - обновленный объект.
     */
    public void update(Item item) {
        String sql = "UPDATE item SET item_name = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement ps = this.util.getConnection().prepareStatement(sql);
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setInt(3, item.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            this.util.disconnect();
        }
    }

    /**
     * Метод удаляет объект из базы.
     *
     * @param item - удаляемый объект
     */
    public void delete(Item item) {
        try {
            PreparedStatement ps = this.util.getConnection().prepareStatement("DELETE FROM item WHERE id=?");
            ps.setInt(1, item.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            this.util.disconnect();
        }
    }

    /**
     * Метод возвращает все объекты, содержащиеся в базе.
     *
     * @return -  список найденных объектов.
     */
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>(1000);
        Statement st = null;
        ResultSet rs = null;
        try {
            st = this.util.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM item");
            items = this.toList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            this.util.setClose(rs);
            this.util.statementClose(st);
            this.util.disconnect();
        }
        return items;
    }

    /**
     * Метод производит поиск объектов в массиве по строковому ключу.
     *
     * @param key - строковый ключ.
     * @return - список найденных объектов
     */
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>(1000);
        ResultSet rs = null;
        try {
            PreparedStatement ps = this.util.getConnection().prepareStatement("SELECT * FROM item WHERE item_name LIKE ?");
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            items = this.toList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            this.util.setClose(rs);
            this.util.disconnect();
        }
        return items;
    }

    /**
     * Метод возвращает список объектов из ResultSet.
     *
     * @param rs - ResultSet запроса из базы
     * @return - список объектов
     * @throws SQLException - выбрасывается при ошибке обращения к базе
     */
    private List<Item> toList(ResultSet rs) throws SQLException {
        List<Item> items = new ArrayList<>(1000);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("item_name");
            String desc = rs.getString("description");
            Timestamp create = rs.getTimestamp("create_date");
            Item item = new Item(id, name, desc, create);
            items.add(item);
        }
        return items;
    }

    /**
     * Метод производит поиск объекта в базе по его Id.
     *
     * @param id - Id объекта
     * @return - найденный объект.
     */
    public Item findById(int id) {
        Statement st = null;
        ResultSet rs = null;
        Item item = null;
        try {
            st = this.util.getConnection().createStatement();
            rs = st.executeQuery("SELECT * FROM item WHERE id=" + id);
            item = this.toList(rs).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.util.setClose(rs);
            this.util.statementClose(st);
            this.util.disconnect();
        }
        return item;
    }

}
