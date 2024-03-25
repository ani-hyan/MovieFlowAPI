package com.example.movieflowapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "rating", schema = "movie_flow")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating_source", nullable = false)
    private String ratingSource;

    @Column(name = "rate", nullable = false)
    private Double rate;

    @Column(name = "max_rate", nullable = false)
    private Double maxRate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", ratingSource='" + ratingSource + '\'' +
                ", rate=" + rate +
                ", maxRate=" + maxRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id, rating.id) && Objects.equals(ratingSource, rating.ratingSource) && Objects.equals(rate, rating.rate) && Objects.equals(maxRate, rating.maxRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratingSource, rate, maxRate);
    }
}
