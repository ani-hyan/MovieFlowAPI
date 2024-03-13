package com.example.movieflowapi.repository;

import com.example.movieflowapi.model.dto.Movie;
import com.example.movieflowapi.model.mapper.MovieRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MovieDAO {
    private final NamedParameterJdbcTemplate template;
    private final MovieRowMapper movieRowMapper;
    private static final String COMEDY_FILTER_QUERY = "SELECT * FROM movie_flow.movie m " +
            "INNER JOIN movie_flow.n2n_movie_to_actor ma ON m.id = ma.movie_id " +
            "INNER JOIN movie_flow.actor a ON ma.actor_id = a.id " +
            "INNER JOIN movie_flow.n2n_movie_to_genre mg ON m.id = mg.movie_id " +
            "INNER JOIN movie_flow.def_genre g ON mg.genre_id = g.id " +
            "LEFT JOIN movie_flow.rating r ON m.id = r.movie_id " +
            "WHERE LOWER(a.first_name) = LOWER(:name) " +
            "AND ((r.max_rate = 10 AND r.rate > 8) OR (r.max_rate = 5 AND r.rate > 4)) " +
            "AND g.name = 'Comedy'";



    public List<Movie> getComediesBasedOnNames(List<String> names) {
        return names.stream()
                .map(x -> {
                    SqlParameterSource parameterSource = new MapSqlParameterSource("name", x);
                    return template.query(COMEDY_FILTER_QUERY, parameterSource, movieRowMapper);
                }).flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

}
