package ru.job4j.crud.model;

/**
 * Город.
 *
 * @author Pyotr Kukharenka
 * @since 06.03.2018
 */

public class City {

    private int id;
    private String cityName;
    private int countryId;

    public City() {
    }

    public City(int id) {
        this.id = id;
    }

    public City(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "City{"
                + "id=" + id
                + ", cityName=" + cityName
                + ", countryId=" + countryId
                + '}';
    }
}
