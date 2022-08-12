package com.movieappV2.dto.converter;

import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.MovieDto;
import com.movieappV2.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieDtoConverter {

    public MovieDto convert(Movie movie) {
        return new MovieDto(movie.getName(),
                movie.getDescription(),
                movie.getRating(),
                movie.getProducer(),
                movie.getReleaseYear(),
                movie.getLanguage(),
                movie.getSubTitleLanguage(),
                movie.getLink(),
                new CategoryDto(movie.getName()));
    }

    public List<MovieDto> convert(List<Movie> movies) {
        return movies.stream().map(this::convert).collect(Collectors.toList());
    }
}
