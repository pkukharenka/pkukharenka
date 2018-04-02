package ru.job4j.crud.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.util.DbConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Country dao
 *
 * @author Pyotr Kukharenka
 * @since 08.03.2018
 */

public class CountryStore implements Store {

    private static final Logger LOG = LoggerFactory.getLogger(CountryStore.class);
    private final DbConnect connect = DbConnect.getInstance();

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try (Connection cn = this.connect.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM country")) {
            while (rs.next()) {
                countries.add(new Country(
                        rs.getInt("id"),
                        rs.getString("country_name")
                ));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return countries;
    }
}
