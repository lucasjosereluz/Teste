package com.example.catalogmovies.dtos;

import com.example.catalogmovies.models.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String title;
    private Integer rate;
    private List<MovieCategory> categories;
}
