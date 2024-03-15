package com.example.movieflowapi.model.dto;

import lombok.Builder;

@Builder
public record Genre(
        Integer id,
        String name
) {
}
