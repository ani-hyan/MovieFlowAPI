package com.example.movieflowapi.model.dto;

import lombok.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;
@Builder
public record Actor(
        String firstName,
        String lastName,
        String movieName
) {
    public static Actor movieMapper(ResultSet rs) throws SQLException {
        return Actor.builder()
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .movieName(rs.getString("title"))
                .build();
    }
}
