package ru.job4j.crud.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.util.DbConnect;
import ru.job4j.crud.model.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Role dao
 *
 * @author Pyotr Kukharenka
 * @since 02.03.2018
 */

public class RoleStore implements Store<Role> {

    private static final Logger LOG = LoggerFactory.getLogger(RoleStore.class);

    private final DbConnect dataSource = DbConnect.getInstance();

    @Override
    public List<Role> findAll() {
        final List<Role> roles = new ArrayList<>();
        try (Connection cn = this.dataSource.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM role")) {
            while (rs.next()) {
                roles.add(new Role(
                        rs.getInt("id"),
                        rs.getString("type")
                ));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return roles;
    }

}
