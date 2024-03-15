package com.example.movieflowapi.repository;

import com.example.movieflowapi.model.dto.Actor;
import com.example.movieflowapi.model.dto.Movie;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MovieDAO {
    private final NamedParameterJdbcTemplate template;
    private static final String COMEDY_FILTER_BY_NAME_QUERY = "SELECT * FROM movie_flow.movie m " +
            "INNER JOIN movie_flow.n2n_movie_to_actor ma ON m.id = ma.movie_id " +
            "INNER JOIN movie_flow.actor a ON ma.actor_id = a.id " +
            "INNER JOIN movie_flow.n2n_movie_to_genre mg ON m.id = mg.movie_id " +
            "INNER JOIN movie_flow.def_genre g ON mg.genre_id = g.id " +
            "LEFT JOIN movie_flow.rating r ON m.id = r.movie_id " +
            "WHERE a.first_name IN (:names) " +
            "AND ((r.max_rate = 10 AND r.rate > 8) OR (r.max_rate = 5 AND r.rate > 4)) " +
            "AND g.name = 'Comedy'";

    private static final String MOVIE_BY_GENRE_QUERY = "SELECT * FROM movie_flow.movie m" +
            "INNER JOIN movie_flow.n2n_movie_to_genre mg on m.id = mg.movie_id " +
            "INNER JOIN movie_flow.def_genre g ON mg.genre_id = g.id " +
            "WHERE g.name = (:genre) " +
            "ORDER BY m.title ASC ";

    private static final String MOVIE_LAST_10_MONTHS_QUERY = "SELECT * FROM movie_flow.movie m " +
            "WHERE m.release_date > CURRENT_DATE - INTERVAL '10 months' " +
            "ORDER BY m.title ASC";

    private static final String ACTOR_BASED_ON_RATING_QUERY = "SELECT * FROM movie_flow.actor a " +
            "INNER JOIN movie_flow.n2n_movie_to_actor ma ON ma.actor_id = a.id " +
            "INNER JOIN movie_flow.movie m on m.id = ma.movie_id " +
            "LEFT JOIN movie_flow.rating r ON m.id = r.movie_id " +
            "WHERE ((r.max_rate = 10 AND r.rate < 4) OR (r.max_rate = 5 AND r.rate < 2))";


    public List<Movie> getMoviesBasedOnGenre(String genre) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("genre", genre);
        return executeQuery(MOVIE_BY_GENRE_QUERY, parameterSource, (rs, rowNum) -> Movie.movieMapper(rs));
    }

    public List<Movie> getRecentlyReleasedMovies() {
        return executeQuery(MOVIE_LAST_10_MONTHS_QUERY, null, (rs, rowNum) -> Movie.movieMapper(rs));
    }

    public List<Actor> getActorsBasedOnRating() {
        return executeQuery(ACTOR_BASED_ON_RATING_QUERY, null, (rs, rowNum) -> Actor.movieMapper(rs));
    }

    public List<Movie> getComediesBasedOnActorNames(List<String> names) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("names", names);
        return executeQuery(COMEDY_FILTER_BY_NAME_QUERY, parameterSource, (rs, rowNum) -> Movie.movieMapper(rs));
    }

    private <T> List<T> executeQuery(String sql, SqlParameterSource parameterSource, RowMapper<T> rowMapper) {
        try {
            if (parameterSource != null) {
                return template.query(sql, parameterSource, rowMapper);
            } else {
                return template.query(sql, rowMapper);
            }
        } catch (DataAccessException e) {
            log.error("Error occurred while executing SQL query: {}", sql, e);
            return null;
        }
    }


}
