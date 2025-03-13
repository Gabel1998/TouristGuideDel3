package com.example.touristguidedel3.RowMappers;

import com.example.touristguidedel3.Model.City;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setCityId(rs.getInt("CityID"));
        city.setCityName(rs.getString("CityName"));
        return city;
    }
}
