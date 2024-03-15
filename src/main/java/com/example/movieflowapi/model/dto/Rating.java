package com.example.movieflowapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
public record Rating(
        Integer id,
        String ratingSource,
        Double rate,
        Double maxRate,
        Integer movieId
) {
    public static Rating ratingMapper(ResultSet rs) throws SQLException {
        return Rating.builder()
                .id(rs.getInt("rating_id"))
                .ratingSource(rs.getString("rating_source"))
                .rate(rs.getDouble("rate"))
                .maxRate(rs.getDouble("max_rate"))
                .build();
    }
}
