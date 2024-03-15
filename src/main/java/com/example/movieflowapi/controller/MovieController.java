package com.example.movieflowapi.controller;

import com.example.movieflowapi.model.dto.Actor;
import com.example.movieflowapi.model.dto.Movie;
import com.example.movieflowapi.repository.MovieDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
@Slf4j
@Validated
public class MovieController {

    private final MovieDAO movieDao;

    @GetMapping("/comedies")
    public ResponseEntity<List<Movie>> getComediesBasedOnNames(@RequestParam(value = "names") @NotNull List<String> names) {
        return ResponseEntity.ok(movieDao.getComediesBasedOnNames(names));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Movie>> getMoviesBasedOnGenre(@RequestParam(value = "genre") @NotNull String genre) {
        return ResponseEntity.ok(movieDao.getMoviesBasedOnGenre(genre));
    }

    @GetMapping("/date")
    public ResponseEntity<List<Movie>> getRecentlyReleasedMovies() {
        return ResponseEntity.ok(movieDao.getRecentlyReleasedMovies());
    }

    @GetMapping("/actor")
    public ResponseEntity<List<Actor>> getActorsWithLowRating() {
        return ResponseEntity.ok(movieDao.getActorsBasedOnRating());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("Invalid request: {}", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
