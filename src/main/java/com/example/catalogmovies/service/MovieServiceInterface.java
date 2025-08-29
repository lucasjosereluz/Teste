package com.example.catalogmovies.service;

import com.example.catalogmovies.dtos.MovieDTO;
import com.example.catalogmovies.models.Movie;
import com.example.catalogmovies.models.MovieCategory;

import java.util.List;
import java.util.UUID;

public interface MovieServiceInterface {

    void save(MovieDTO movieDTO);

    void delete(UUID id, Movie movie);

    void update(UUID id, MovieDTO movieDTO);

    List<Movie> findByRateLessThanEqual (Integer rate);

    List<Movie> findByRateGreaterThanEqual (Integer rate);

    List<Movie> findByCategoriesIn (List<MovieCategory> categories);

    List<Movie> findByTitleLike(String title);

    List<Movie> findByCategoriesInAndRateEquals(List<MovieCategory> categories, Integer rate);

    void MapDTOToEntity(Movie movie, MovieDTO movieDTO);
}
