package com.example.touristguidedel3.RowMappers;

import com.example.touristguidedel3.Model.Tag;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRowMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setTagId(rs.getInt("TagID"));
        tag.setTagName(rs.getString("TagName"));
        return tag;
    }
}
