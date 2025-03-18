package com.example.touristguidedel3.Repository;

import com.example.touristguidedel3.Model.City;
import com.example.touristguidedel3.RowMappers.CityRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository implements ICrudOperations{

    private final JdbcTemplate jdbcTemplate;

    public CityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<City> findAll() {
        String sql = "SELECT * FROM cities";
        return jdbcTemplate.query(sql, new CityRowMapper());
    }

    @Override
    public City findById(int cityId) {
        String sql = "SELECT * FROM cities WHERE CityID = ?";
        List<City> results = jdbcTemplate.query(sql, new CityRowMapper(), cityId);
        return results.isEmpty() ? null : results.get(0);
    }

    // Opret en ny city
    public City createCity(City city) {
        String sql = "INSERT INTO cities (CityName) VALUES (?)";
        jdbcTemplate.update(sql, city.getCityName());
        // Evt. retur med KeyHolder, hvis du skal bruge auto-genereret ID
        return city;
    }

    // Opdater city
    public City updateCity(City city) {
        String sql = "UPDATE cities SET CityName = ? WHERE CityID = ?";
        jdbcTemplate.update(sql, city.getCityName(), city.getCityId());
        return city;
    }

    // Slet city
    public void deleteCity(int cityId) {
        String sql = "DELETE FROM cities WHERE CityID = ?";
        jdbcTemplate.update(sql, cityId);
    }


}
