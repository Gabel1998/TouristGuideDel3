package com.example.touristguidedel3.RowMappers;

import com.example.touristguidedel3.Model.City;
import com.example.touristguidedel3.Model.Touristattraction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TouristAttractionRowMapper implements RowMapper<Touristattraction> {

    @Override
    public Touristattraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Touristattraction attraction = new Touristattraction();
        attraction.setId(rs.getInt("AttractionID"));
        attraction.setName(rs.getString("AttractionName"));
        attraction.setDescription(rs.getString("AttractionDescription"));

        // Map city
        City city = new City();
        city.setCityId(rs.getInt("CityID"));
        city.setCityName(rs.getString("CityName"));
        attraction.setCity(city);

        return attraction;
    }
}
