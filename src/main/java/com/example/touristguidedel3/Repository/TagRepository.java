package com.example.touristguidedel3.Repository;

import com.example.touristguidedel3.Model.Tag;
import com.example.touristguidedel3.RowMappers.TagRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {

    private final JdbcTemplate jdbcTemplate;

    public TagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> findAllTags() {
        String sql = "SELECT * FROM tags";
        return jdbcTemplate.query(sql, new TagRowMapper());
    }

    public Tag findTagById(int tagId) {
        String sql = "SELECT * FROM tags WHERE TagsID = ?";
        List<Tag> results = jdbcTemplate.query(sql, new TagRowMapper(), tagId);
        return results.isEmpty() ? null : results.get(0);
    }

    public Tag createTag(Tag tag) {
        String sql = "INSERT INTO tags (TagName) VALUES (?)";
        jdbcTemplate.update(sql, tag.getTagName());
        return tag;
    }

    public Tag updateTag(Tag tag) {
        String sql = "UPDATE tags SET TagName = ? WHERE TagID = ?";
        jdbcTemplate.update(sql, tag.getTagName(), tag.getTagId());
        return tag;
    }

    public void deleteTag(int tagId) {
        String sql = "DELETE FROM tags WHERE TagID = ?";
        jdbcTemplate.update(sql, tagId);
    }
}
