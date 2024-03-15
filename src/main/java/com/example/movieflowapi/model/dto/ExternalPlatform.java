package com.example.movieflowapi.model.dto;

import java.time.LocalDate;


public record ExternalPlatform(
        Integer id,
        String name,
        LocalDate foundedDate
) {
}
