package com.example.catalogmovies.controller;

import com.example.catalogmovies.dtos.MovieDTO;
import com.example.catalogmovies.models.Movie;
import com.example.catalogmovies.models.MovieCategory;
import com.example.catalogmovies.service.MovieService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MovieController {

    @Autowired
    public MovieService service;

    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDTO movieDTO) {
        service.save(movieDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/movies/greater/{rate}")
    public ResponseEntity<List<Movie>> getMoviesByRateGreaterThan(@PathVariable("rate") Integer rate) {
        try {
            List<Movie> movies = service.findByRateGreaterThanEqual(rate);
            return ResponseEntity.ok().body(movies);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("/movies/less/{rate}")
    public ResponseEntity<List<Movie>> getMoviesByRateLessThan(@PathVariable("rate") Integer rate) {
        try {
            List<Movie> movies = service.findByRateLessThanEqual(rate);
            return ResponseEntity.ok().body(movies);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("movies/{categories}")
    public ResponseEntity<List<Movie>> getByCategories(@PathVariable("categories") List<MovieCategory>  categories){
        try{
            List<Movie> movie = service.findByCategoriesIn(categories);
            return ResponseEntity.ok().body(movie);
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") UUID id, @RequestBody MovieDTO movieDTO) {
        service.update(id, movieDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<List<Movie>> getByTitle(@PathVariable("title") String title){
        var movie = service.findByTitleLike(title);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping("/movies/categories-rate")
    public ResponseEntity<List<Movie>>
    getByCategoriesAndRate(@RequestParam List<MovieCategory> categories,
                           @RequestParam Integer rate){
        List<Movie> movie = service.findByCategoriesInAndRateEquals(categories, rate);
        return ResponseEntity.ok().body(movie);
    }
}
