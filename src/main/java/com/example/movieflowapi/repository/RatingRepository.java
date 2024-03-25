package com.example.movieflowapi.repository;

import com.example.movieflowapi.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
