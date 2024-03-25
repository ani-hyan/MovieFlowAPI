package com.example.movieflowapi.model.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie", schema = "movie_flow")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "title")
    private String title;

    @Column(name = "duration_mins")
    private Integer durationMinutes;

    @Column(name = "language", nullable = false)
    private String language;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public void addRating(Rating rating){
        ratings.add(rating);
        rating.setMovie(this);
    }

    public void removeRating(Rating rating){
        ratings.remove(rating);
        rating.setMovie(null);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(title, movie.title) && Objects.equals(durationMinutes, movie.durationMinutes) && Objects.equals(language, movie.language) && Objects.equals(ratings, movie.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, releaseDate, title, durationMinutes, language, ratings);
    }


}
