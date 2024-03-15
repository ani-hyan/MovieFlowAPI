package com.example.movieflowapi.model.dto;

import java.time.LocalDate;

public record ProviderCompany(
        Integer id,
        String name,
        LocalDate foundedDate
) {
}
