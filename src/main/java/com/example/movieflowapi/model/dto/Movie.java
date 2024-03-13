package com.example.movieflowapi.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class Movie {
    private Integer id;
    private Date releaseDate;
    private String description;
    private Integer durationMins;
    private String language;
}
