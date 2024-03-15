package com.example.movieflowapi.model.dto;

import lombok.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Builder
public record Movie(
        Integer id,
        LocalDate releaseDate,
        String title,
        Integer durationMins,
        String language
) {
    public static Movie movieMapper(ResultSet rs) throws SQLException {
        return Movie.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .durationMins(rs.getInt("duration_mins"))
                .language(rs.getString("language"))
                .releaseDate(rs.getDate("release_date").toLocalDate())
                .build();
    }
}
