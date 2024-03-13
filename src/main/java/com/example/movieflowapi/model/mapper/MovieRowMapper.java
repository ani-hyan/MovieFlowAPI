package com.example.movieflowapi.model.mapper;

import com.example.movieflowapi.model.dto.Movie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setDescription(rs.getString("description"));
        movie.setDurationMins(rs.getInt("duration_mins"));
        movie.setLanguage(rs.getString("language"));
        movie.setReleaseDate(rs.getDate("release_date"));
        return movie;
    }

}
