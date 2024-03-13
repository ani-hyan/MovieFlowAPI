package com.example.movieflowapi.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Rating {
    private int id;
    private String ratingSource;
    private double rate;
    private double maxRate;
    private int movieId;
}
