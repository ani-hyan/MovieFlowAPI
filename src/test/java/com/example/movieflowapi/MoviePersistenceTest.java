package com.example.movieflowapi;

import com.example.movieflowapi.model.entity.Movie;
import com.example.movieflowapi.repository.MovieRepository;
import jakarta.persistence.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.example.movieflowapi.MovieUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoviePersistenceTest {
    @Autowired
    private MovieRepository movieRepository;
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
        movieRepository.deleteAll();
    }

    @Test
    void saveMovieUsingPersistNoId() {
        Movie movie = getMovieMatrix(null);

        entityManager.persist(movie);
        entityManager.flush();
        tx.commit();
        entityManager.close();

        assertNotNull(movie.getId());
    }

    @Test
    void saveMovieUsingMergeNoId(){
        Movie movie = getMovieMatrix(null);
        Movie savedMovie = entityManager.merge(movie);

        tx.commit();
        entityManager.close();

        assertNotNull(savedMovie.getId());
        assertNull(movie.getId());
    }
    @Test
    void saveMovieUsingRepositoryNoId(){
        Movie savedMovie = movieRepository.save(getMovieMatrix(null));

        assertNotNull(savedMovie.getId());
    }

    @Test
    void saveMovieUsingPersistWithId() {
        Movie movie = getMovieMatrix(MATRIX_ID);

        assertThrows(EntityExistsException.class, () -> entityManager.persist(movie));
        entityManager.flush();

        tx.commit();
        entityManager.close();
    }
    @Test
    void saveMovieUsingMergeWithId(){
        Movie movie = getMovieMatrix(MATRIX_ID);

        Movie savedMovie = entityManager.merge(movie);

        tx.commit();
        entityManager.close();

        assertNotNull(savedMovie.getId());
    }
    @Test
    void saveMovieUsingRepositoryWithId(){
        Movie savedMovie = movieRepository.save(getMovieMatrix(MATRIX_ID));

        assertNotNull(savedMovie.getId());
    }


    @Test
    void saveMovieUsingPersistWithExistingId() {
        Movie movieMatrix = getMovieMatrix(MATRIX_ID);
        Movie movieAmelie = getMovieMatrix(AMELIE_ID);

        movieRepository.save(movieMatrix);
        assertThrows(EntityExistsException.class, () -> entityManager.persist(movieAmelie));
        entityManager.flush();
    }
    @Test
    void saveMovieUsingMergeWithExistingId(){
        Movie movieMatrix = getMovieMatrix(MATRIX_ID);

        Movie savedMovieMatrix = movieRepository.save(movieMatrix);

        Movie movieAmelie = getMovieAmelie(savedMovieMatrix.getId());
        Movie savedMovieAmelie = entityManager.merge(movieAmelie);

        tx.commit();
        entityManager.close();

        assertNotNull(savedMovieAmelie);
        assertNotNull(savedMovieAmelie.getId());
        assertEquals(savedMovieMatrix.getId(), savedMovieAmelie.getId());
    }
    @Test
    void saveMovieUsingRepositoryWithExistingId(){
        movieRepository.save(getMovieMatrix(MATRIX_ID));

        Movie savedMovieMatrix = movieRepository.findByTitle(MATRIX_TITLE);
        Movie savedMovieAmelie = movieRepository.save(getMovieAmelie(savedMovieMatrix.getId()));

        assertNull(movieRepository.findByTitle(MATRIX_TITLE));
        assertNotEquals(savedMovieAmelie.getId(), AMELIE_ID);
    }

    @Test
    void updateMovieWithoutSaving(){
        movieRepository.save(getMovieMatrix(null));
        Movie retrievedMovie = movieRepository.findByTitle(MATRIX_TITLE);

        retrievedMovie.setTitle(AMELIE_TITLE);
        entityManager.flush();
        entityManager.clear();
        tx.commit();
        entityManager.close();

        Optional<Movie> updatedMovie = movieRepository.findById(retrievedMovie.getId());

        assertNotNull(updatedMovie);
        assertNotEquals(AMELIE_TITLE, updatedMovie.get().getTitle());
    }

}
