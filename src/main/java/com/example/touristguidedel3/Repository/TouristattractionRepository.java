package com.example.touristguidedel3.Repository;

import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.RowMappers.TouristAttractionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristattractionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristattractionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter alle attractions (evt. join til city)
    public List<Touristattraction> getAllAttractions() {
        // JOIN til city for at få CityName med
        String sql = """
            SELECT a.*, c.CityName
            FROM attractions a
            JOIN cities c ON a.CityID = c.CityID
        """;
        return jdbcTemplate.query(sql, new TouristAttractionRowMapper());
    }

    // Hent én attraction via ID
    public Touristattraction getAttractionById(int id) {
        String sql = """
            SELECT a.*, c.CityName
            FROM attractions a
            JOIN cities c ON a.CityID = c.CityID
            WHERE a.AttractionID = ?
        """;
        List<Touristattraction> results = jdbcTemplate.query(sql, new TouristAttractionRowMapper(), id);
        return results.isEmpty() ? null : results.get(0);
    }

    // Evt. hent en attraction via Name
    public Touristattraction getAttractionByName(String name) {
        String sql = """
            SELECT a.*, c.CityName
            FROM attractions a
            JOIN cities c ON a.CityID = c.CityID
            WHERE a.AttractionName = ?
        """;
        List<Touristattraction> results = jdbcTemplate.query(sql, new TouristAttractionRowMapper(), name);
        if (results.isEmpty()) return null;
        Touristattraction touristattraction = results.get(0);

        int attractionId = touristattraction.getId();  /*henter og sætter tags for denne attraction*/
        List<Tag> tags = getAttractionsTags(attractionId);
        touristattraction.setTags(tags);

        return touristattraction;

    }

    // Gem en ny attraction (uden at returnere genereret PK)
    public Touristattraction saveAttraction(Touristattraction t) {
        String sql = """
            INSERT INTO attractions (AttractionName, AttractionDescription, CityID)
            VALUES (?,?,?)
        """;
        jdbcTemplate.update(sql, t.getName(), t.getDescription(), t.getCity().getCityId());
        return t;
    }

    // Opdater en attraction
    public Touristattraction updateAttraction(Touristattraction t) {
        String sql = """
            UPDATE attractions
            SET AttractionName = ?, AttractionDescription = ?, CityID = ?
            WHERE AttractionID = ?
        """;
        jdbcTemplate.update(sql, t.getName(), t.getDescription(), t.getCity().getCityId(), t.getId());
        return t;
    }

    // Slet en attraction
    public Touristattraction deleteAttraction(String name) {
        // Find den først, så vi kan returnere det slettede obj
        Touristattraction toDelete = getAttractionByName(name);
        if (toDelete != null) {
            String sql = "DELETE FROM attractions WHERE AttractionName = ?";
            jdbcTemplate.update(sql, name);
        }
        return toDelete;
    }

    // Hent de tags, der hører til en attraction (forudsat en join-tabel attraction_tags)
    public List<Tag> getAttractionsTags(int attractionId) {
        String sql = """
            SELECT t.*
            FROM tags t
            JOIN attraction_tags at ON t.TagsID = at.TagsID
            WHERE at.AttractionsAttractionID = ?
        """;
        // Genbrug TagRowMapper
        return jdbcTemplate.query(sql, new com.example.touristguidedel3.RowMappers.TagRowMapper(), attractionId);
    }

    // Tilføj et tag til en attraction (fx i en koblingstabel)
    public void addTagToAttraction(int attractionId, int tagId) {
        String sql = "INSERT INTO attraction_tags (AttractionID, TagID) VALUES (?, ?)";
        jdbcTemplate.update(sql, attractionId, tagId);
    }
}
