package com.example.touristguidedel3.Model;

public class City {

    private int cityId;
    private String cityName;

    // Tom constructor (kræves af bl.a. RowMapper)
    public City() {}

    // Ekstra constructor, hvis du vil bruge den
    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    // Getters og setters
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
