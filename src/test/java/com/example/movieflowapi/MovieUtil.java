package com.example.movieflowapi;

import com.example.movieflowapi.model.entity.Movie;
import com.example.movieflowapi.model.entity.Rating;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class MovieUtil {
    public static final Long MATRIX_ID = 200L;
    public static final String MATRIX_TITLE = "The Matrix";
    public static final Integer MATRIX_DURATION = 200;
    public static final String MATRIX_LANGUAGE = "English";
    public static final LocalDate MATRIX_RELEASE_DATE = LocalDate.of(1999, 3, 31);
    public static final Long AMELIE_ID = 200L;
    public static final String RATING_SOURCE = "IMDb";
    public static final Double RATE = 8.5;
    public static final Double MAX_RATE = 10.0;
    public static final Rating RATING = Rating.builder()
            .ratingSource(RATING_SOURCE)
            .rate(RATE)
            .maxRate(MAX_RATE)
            .build();
    public static final String AMELIE_TITLE = "Amelie";
    public static final int AMELIE_DURATION = 122;
    public static final LocalDate AMELIE_RELEASE_DATE = LocalDate.of(2001, 2, 25);
    public static final String AMELIE_LANGUAGE = "French";

    public static Movie getMovieMatrix(Long matrixId) {
        Movie.MovieBuilder movieBuilder = Movie.builder()
                .title(MATRIX_TITLE)
                .durationMinutes(MATRIX_DURATION)
                .releaseDate(MATRIX_RELEASE_DATE)
                .language(MATRIX_LANGUAGE)
                .ratings(new ArrayList<>());

        return Optional.ofNullable(matrixId)
                .map(id -> movieBuilder.id(id).build())
                .orElseGet(movieBuilder::build);
    }

    public static Movie getMovieAmelie(Long amelieId) {
        Movie.MovieBuilder movieBuilder = Movie.builder()
                .title(AMELIE_TITLE)
                .durationMinutes(AMELIE_DURATION)
                .releaseDate(AMELIE_RELEASE_DATE)
                .language(AMELIE_LANGUAGE)
                .ratings(new ArrayList<>());

        return Optional.ofNullable(amelieId)
                .map(id -> movieBuilder.id(id).build())
                .orElseGet(movieBuilder::build);
    }

    public static Rating getRating(Movie movie){
        return Rating.builder()
                .movie(movie)
                .ratingSource(RATING_SOURCE)
                .rate(RATE)
                .maxRate(MAX_RATE)
                .build();
    }
}
