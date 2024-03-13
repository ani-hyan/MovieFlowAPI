package com.example.movieflowapi.model.response;

import com.example.movieflowapi.model.dto.Actor;
import com.example.movieflowapi.model.dto.Genre;
import com.example.movieflowapi.model.dto.Movie;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Data
@Component
public class MovieResponse {
    private Movie movie;
    private List<Actor> actors;
    private List<Genre> genres;
}
