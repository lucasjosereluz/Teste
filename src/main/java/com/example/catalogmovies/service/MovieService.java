package com.example.catalogmovies.service;

import com.example.catalogmovies.dtos.MovieDTO;
import com.example.catalogmovies.models.Movie;
import com.example.catalogmovies.models.MovieCategory;
import com.example.catalogmovies.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.UUID;

@Service
@ApplicationScope
@AllArgsConstructor
public class MovieService implements MovieServiceInterface {

    @Autowired
    private final MovieRepository repository;

    @Override
    public void save(MovieDTO movieDTO) {
        var movie = new Movie();
        MapDTOToEntity(movie, movieDTO);
        repository.save(movie);
        System.out.println("Movie saved!");
    }

    @Override
    public void delete(UUID id, Movie movie) {
        repository.deleteById(id);
    }

    @Override
    public void update(UUID id, MovieDTO movieDTO) {
        var existingMovie = repository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        MapDTOToEntity(existingMovie, movieDTO);
        repository.save(existingMovie);
        System.out.println("Movie Updated!");
    }


    @Override
    public List<Movie> findByRateLessThanEqual(Integer rate) {
        return repository.findByRateLessThanEqual(rate);
    }

    @Override
    public List<Movie> findByRateGreaterThanEqual(Integer rate) {
        try {
            System.out.println("Finding movie by rate greater: " + rate);
            return repository.findByRateGreaterThanEqual(rate);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Movie> findByCategoriesIn(List<MovieCategory> categories) {
        return repository.findByCategoriesIn(categories);
    }

    @Override
    public List<Movie> findByTitleLike(String title) {
        return repository.findByTitleLike(title);
    }

    @Override
    public List<Movie> findByCategoriesInAndRateEquals(List<MovieCategory> categories, Integer rate) {
        return repository.findByCategoriesInAndRateEquals(categories, rate);
    }

    @Override
    public void MapDTOToEntity(Movie movie, MovieDTO movieDTO) {
        movie.setTitle(movieDTO.getTitle());
        movie.setRate(movieDTO.getRate());
        movie.setCategories(movieDTO.getCategories());
    }
}
