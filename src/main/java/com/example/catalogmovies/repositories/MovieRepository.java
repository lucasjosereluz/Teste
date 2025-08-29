package com.example.catalogmovies.repositories;

import com.example.catalogmovies.models.Movie;
import com.example.catalogmovies.models.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

    List<Movie> findByRateLessThanEqual(Integer rate);

    List<Movie> findByRateGreaterThanEqual(Integer rate);

    List<Movie> findByCategoriesIn(List<MovieCategory> categories);

    List<Movie> findByCategoriesInAndRateEquals(List<MovieCategory> categories, Integer rates);

    List<Movie> findByTitleLike(String title);
}
