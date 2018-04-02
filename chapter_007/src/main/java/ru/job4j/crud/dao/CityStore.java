package ru.job4j.crud.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.City;
import ru.job4j.crud.util.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * City dao
 *
 * @author Pyotr Kukharenka
 * @since 08.03.2018
 */

public class CityStore {

    private static final Logger LOG = LoggerFactory.getLogger(CityStore.class);
    private final DbConnect connect = DbConnect.getInstance();

    public List<City> findCitiesByCountryId(int id) {
        List<City> cities = new ArrayList<>();
        try (Connection cn = this.connect.getConnection();
             PreparedStatement pst = cn.prepareStatement("SELECT * FROM city WHERE country_id=?")) {
            pst.setInt(1, id);
            pst.executeQuery();
            try (ResultSet rs = pst.getResultSet()) {
                while (rs.next()) {
                    cities.add(new City(
                            rs.getInt("id"),
                            rs.getString("city_name")
                    ));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return cities;
    }
}
