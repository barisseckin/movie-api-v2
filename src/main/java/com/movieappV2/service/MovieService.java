package com.movieappV2.service;

import com.movieappV2.dto.MovieDto;
import com.movieappV2.dto.converter.MovieDtoConverter;
import com.movieappV2.dto.request.CreateMovieRequest;
import com.movieappV2.dto.request.UpdateMovieRequest;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.Category;
import com.movieappV2.model.Movie;
import com.movieappV2.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieDtoConverter movieDtoConverter;
    private final CategoryService categoryService;


    public MovieDto save(CreateMovieRequest request) {
        Category category = categoryService.getCategoryByName(request.getCategoryName());

        Movie movie = new Movie(request.getName(),
                request.getDescription(),
                request.getProducer(),
                request.getReleaseYear(),
                request.getLanguage(),
                request.getSubTitleLanguage(),
                LocalDate.now(),
                request.getLink(),
                request.getImageUrl(),
                category);

        return movieDtoConverter.convert(movieRepository.save(movie));
    }

    public List<MovieDto> getAll() {
        return movieDtoConverter.convert(movieRepository.findAll());
    }

    public List<MovieDto> getByCategoryName(String categoryName) {
        return movieDtoConverter.convert(movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getCategory().getName().equals(categoryName))
                .collect(Collectors.toList()));
    }

    public List<MovieDto> getByRating(int rating) {
        return movieDtoConverter.convert(movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getRating() >= rating)
                .collect(Collectors.toList()));
    }

    public MovieDto getByName(String name) {
        return movieDtoConverter.convert(movieRepository.findMovieByName(name).orElseThrow(() ->
                new NotFoundException("movie not found, movie name: " + name)));
    }

    public List<MovieDto> searchByName(String name) {
        return movieDtoConverter.convert(movieRepository.findAll()
                .stream().filter(movie -> movie.getName().contains(name))
                .collect(Collectors.toList()));
    }

    public void deleteByName(String name) {
        Movie movie = movieRepository.findMovieByName(name).orElseThrow(() -> new NotFoundException("movie not found, movie name: " + name));
        movieRepository.deleteById(movie.getId());
    }

    public MovieDto update(String name, UpdateMovieRequest request) {
        Category category = categoryService.getCategoryByName(request.getCategoryName());

        Movie movie = getMovieByName(name);
        movie.setName(request.getName());
        movie.setLink(request.getLink());
        movie.setDescription(request.getDescription());
        movie.setImageUrl(request.getImageUrl());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setCategory(category);
        movie.setLanguage(request.getLanguage());
        movie.setSubTitleLanguage(request.getSubTitleLanguage());
        movie.setUpdateDate(LocalDate.now());

        return movieDtoConverter.convert(movieRepository.save(movie));
    }

    protected Movie getMovieByName(String name) {
        return movieRepository.findMovieByName(name).orElseThrow(() -> new NotFoundException("movie not found, movie name: " + name));
    }

}
