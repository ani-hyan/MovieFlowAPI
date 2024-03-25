package com.example.movieflowapi;

import com.example.movieflowapi.model.entity.Movie;
import com.example.movieflowapi.model.entity.Rating;
import com.example.movieflowapi.repository.MovieRepository;
import com.example.movieflowapi.repository.RatingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.example.movieflowapi.MovieUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieRatingPersistenceTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private EntityTransaction tx;

    @BeforeEach
    public void setup(){
        entityManager = entityManagerFactory.createEntityManager();
        tx = entityManager.getTransaction();
        tx.begin();
    }
    @AfterEach
    public void clearUp() {
        ratingRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Test
    void saveNewMovieRatingUsingPersist() {
        Movie movie = getMovieMatrix(null);
        movie.addRating(RATING);

        entityManager.persist(movie);
        entityManager.flush();
        tx.commit();

        Movie retrievedMovie = entityManager.find(Movie.class, movie.getId());
        assertMovieAndRatings(retrievedMovie);
    }

    @Test
    void saveNewMovieRatingUsingMerge() {
        Movie movie = getMovieMatrix(null);
        movie.addRating(RATING);

        Movie savedMovie = entityManager.merge(movie);

        tx.commit();

        Movie retrievedMovie = entityManager.find(Movie.class, savedMovie.getId());
        assertMovieAndRatings(retrievedMovie);
    }

    @Test
    void saveNewMovieRatingUsingSave(){
        Movie movie = getMovieMatrix(null);
        movie.addRating(RATING);

        Movie savedMovie = movieRepository.save(movie);
        assertMovieAndRatings(savedMovie);
    }

    @Test
    void saveMovieRatingUsingPersist() {
        Movie movie = getMovieMatrix(null);
        movie.addRating(RATING);
        entityManager.persist(movie);
        entityManager.flush();
        tx.commit();

        tx.begin();
        Movie retrievedMovie = entityManager.find(Movie.class, movie.getId());
        entityManager.persist(retrievedMovie);
        entityManager.flush();
        tx.commit();

        assertMovieAndRatings(retrievedMovie);
    }

    @Test
    void saveMovieRatingUsingMerge() {
        Movie movie = getMovieMatrix(null);
        movie.addRating(RATING);

        Movie savedMovie1 = entityManager.merge(movie);
        tx.commit();

        tx.begin();
        Movie retrievedMovie = entityManager.find(Movie.class, savedMovie1.getId());
        Movie savedMovie2 = entityManager.merge(retrievedMovie);

        assertMovieAndRatings(savedMovie2);
    }

    @Test
    void saveMovieRatingUsingSave(){
        Movie originalMovie = getMovieMatrix(null);
        originalMovie.addRating(RATING);
        Movie savedOriginalMovie = movieRepository.save(originalMovie);

        Optional<Movie> optionalSavedMovie = movieRepository.findById(savedOriginalMovie.getId());
        Movie retrievedSavedMovie = movieRepository.save(optionalSavedMovie.get());

        assertMovieAndRatings(retrievedSavedMovie);
    }

    private void assertMovieAndRatings(Movie retrievedMovie){
        assertNotNull(retrievedMovie);
        assertFalse(retrievedMovie.getRatings().isEmpty());

        List<Rating> retrievedRating = retrievedMovie.getRatings();

        assertThat(retrievedRating)
                .isNotNull()
                .asInstanceOf(InstanceOfAssertFactories.list(Rating.class))
                .hasSize(1)
                .allSatisfy(rating -> {
                    assertThat(rating.getRatingSource()).isEqualTo(RATING.getRatingSource());
                    assertThat(rating.getRate()).isEqualTo(RATING.getRate());
                    assertThat(rating.getMaxRate()).isEqualTo(RATING.getMaxRate());
                });

        entityManager.close();
    }
}
