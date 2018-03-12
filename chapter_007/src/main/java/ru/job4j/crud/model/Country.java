package ru.job4j.crud.model;


import java.util.List;

/**
 * Страна.
 *
 * @author Pyotr Kukharenka
 * @since 06.03.2018
 */

public class Country {

    private int id;
    private String countryName;
    private List<City> cities;

    public Country() {
    }

    public Country(int id) {
        this.id = id;
    }

    public Country(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{"
                + "id=" + id
                + ", countryName='" + countryName + '\''
                + ", cities=" + cities
                + '}';
    }
}
