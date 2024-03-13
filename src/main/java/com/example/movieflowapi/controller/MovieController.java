package com.example.movieflowapi.controller;

import com.example.movieflowapi.model.dto.Movie;
import com.example.movieflowapi.repository.MovieDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieDAO dao;

    @GetMapping(value = "/comedies")
    public List<Movie> getComedies(@RequestParam(value = "names") List<String> names){
        return dao.getComediesBasedOnNames(names);
    }

}
