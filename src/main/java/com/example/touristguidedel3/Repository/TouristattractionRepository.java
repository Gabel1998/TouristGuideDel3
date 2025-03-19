package com.example.touristguidedel3.Repository;

import com.example.touristguidedel3.Model.Touristattraction;
import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.RowMappers.TouristAttractionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class TouristattractionRepository implements ICrudOperations {

    private final JdbcTemplate jdbcTemplate;

    public TouristattractionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter alle attractions (evt. join til city)
    @Override
    public List<Touristattraction> findAll() {
        // JOIN til city for at få CityName med
        String sql = """
            SELECT a.*, c.CityName
            FROM attractions a
            JOIN cities c ON a.CityID = c.CityID
        """;
        return jdbcTemplate.query(sql, new TouristAttractionRowMapper());
    }

    // Hent én attraction via ID
    @Override
    public Touristattraction findById(int id) {
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getName());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getCity().getCityId());
            return ps;
        }, keyHolder);
        t.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        String sqlTags = """       
            INSERT INTO attraction_tags (AttractionsAttractionID, TagsID)
            VALUES (?,?)
        """;
        for (Tag tag : t.getTags()){
            jdbcTemplate.update(sqlTags,t.getId(),tag.getTagId());
        }
        return t;
    }

    // Opdater en attraction
    public Touristattraction updateAttraction(Touristattraction t) {
        String deleteTagsSql = "DELETE FROM attraction_tags WHERE AttractionsAttractionID = ?";
        jdbcTemplate.update(deleteTagsSql, t.getId());
        String insertTagsSql = "INSERT INTO attraction_tags (AttractionsAttractionID, TagsID) VALUES (?, ?)";

        for (Tag tag : t.getTags()) {
            jdbcTemplate.update(insertTagsSql, t.getId(), tag.getTagId());
        }
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
