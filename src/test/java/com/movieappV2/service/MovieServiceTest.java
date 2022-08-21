package com.movieappV2.service;

import com.movieappV2.TestUtils;
import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.MovieDto;
import com.movieappV2.dto.converter.MovieDtoConverter;
import com.movieappV2.dto.request.CreateMovieRequest;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.Category;
import com.movieappV2.model.Movie;
import com.movieappV2.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieServiceTest extends TestUtils {

    private MovieRepository movieRepository;
    private MovieDtoConverter movieDtoConverter;
    private CategoryService categoryService;

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieRepository = mock(MovieRepository.class);
        movieDtoConverter = mock(MovieDtoConverter.class);
        categoryService = mock(CategoryService.class);

        movieService = new MovieService(movieRepository, movieDtoConverter, categoryService);
    }

    @Test
    public void testGetAll_itShouldReturnMovieDtoLİst() {
        List<Movie> movieList = generateMovieList();
        List<MovieDto> movieDtoList = generateMovieDtoList(movieList);

        when(movieRepository.findAll()).thenReturn(movieList);
        when(movieDtoConverter.convert(movieList)).thenReturn(movieDtoList);

        List<MovieDto> result = movieService.getAll();

        assertEquals(result, movieDtoList);
        verify(movieRepository).findAll();
        verify(movieDtoConverter).convert(movieList);
    }

    @Test
    public void testGetByRating_itShouldReturnMovieDtoList() {
        int rating = 5;

        List<Movie> movieList = generateMovieList();
        List<MovieDto> movieDtoList = generateMovieDtoList(movieList);

        when(movieRepository.findAll()).thenReturn(movieList.stream().filter(movie -> movie.getRating() >= rating).collect(Collectors.toList()));
        when(movieDtoConverter.convert(movieList)).thenReturn(movieDtoList);

        List<MovieDto> result = movieService.getByRating(rating);

        assertEquals(result, movieDtoList);
        verify(movieRepository).findAll();
        verify(movieDtoConverter).convert(movieList);
    }

    @Test
    public void testGetByName_itShouldReturnMovieDto() {
        Movie movie = new Movie(1L, "Test-Name", "Test-Description", 5, "Test-Producer", 2022, "Test-Language",
                "Test-SubTitleLanguage", LocalDate.now(), LocalDate.now(), "Test-Link", "Test-ImageUrl",
                new Category(1L, "Test-Name", LocalDate.now(), LocalDate.now()));

        MovieDto movieDto = new MovieDto("Test-Name", "Test-Description", 5, "Test-Producer", 2022, "Test-Language",
                "Test-SubTitleLanguage", "Test-Link", "Test-ImageUrl",
                new CategoryDto("Test-Name"));


        when(movieRepository.findMovieByName("Test-Name")).thenReturn(Optional.of(movie));
        when(movieDtoConverter.convert(movie)).thenReturn(movieDto);

        MovieDto result = movieService.getByName("Test-Name");

        assertEquals(result, movieDto);
        verify(movieRepository).findMovieByName("Test-Name");
        verify(movieDtoConverter).convert(movie);
    }

    @Test
    public void testGetByName_whenMovieNameDoesNotExist_shouldThrowNotFoundException() {
        when(movieRepository.findMovieByName("Test-Name")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.getByName("Test-Name"));

        verify(movieRepository).findMovieByName("Test-Name");
    }

    @Test
    public void testGetMovieByName_itShouldReturnMovie() {
        Movie movie = new Movie(1L, "Test-Name", "Test-Description", 5, "Test-Producer", 2022, "Test-Language",
                "Test-SubTitleLanguage", LocalDate.now(), LocalDate.now(), "Test-Link", "Test-ImageUrl",
                new Category(1L, "Test-Name", LocalDate.now(), LocalDate.now()));

        when(movieRepository.findMovieByName("Test-Name")).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovieByName("Test-Name");

        assertEquals(result, movie);
        verify(movieRepository).findMovieByName("Test-Name");
    }

    @Test
    public void testGetMovieByName_whenMovieNameDoesNotExist_shouldThrowNotFoundException() {
        when(movieRepository.findMovieByName("Test-Name")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> movieService.getMovieByName("Test-Name"));

        verify(movieRepository).findMovieByName("Test-Name");
    }

    @Test
    public void testSearchByName_itShouldReturnMovieDtoList() {
        String search = "Test";

        List<Movie> movieList = generateMovieList();
        List<MovieDto> movieDtoList = generateMovieDtoList(movieList);

        when(movieRepository.findAll()).thenReturn(movieList.stream().filter(movie -> movie.getName().contains(search)).collect(Collectors.toList()));
        when(movieDtoConverter.convert(movieList)).thenReturn(movieDtoList);

        List<MovieDto> result = movieService.searchByName(search);

        assertEquals(result, movieDtoList);
        verify(movieRepository).findAll();
        verify(movieDtoConverter).convert(movieList);
    }

    @Test
    public void testGetByCategoryName_itShouldReturnMovieDtoList() {
        String categoryName = "Test-Name";

        List<Movie> movieList = generateMovieList();
        List<MovieDto> movieDtoList = generateMovieDtoList(movieList);

        when(movieRepository.findAll()).thenReturn(movieList.stream().filter(movie -> movie.getCategory().getName().equals(categoryName)).collect(Collectors.toList()));
        when(movieDtoConverter.convert(movieList)).thenReturn(movieDtoList);

        List<MovieDto> result = movieService.getByCategoryName(categoryName);

        assertEquals(result, movieDtoList);
        verify(movieRepository).findAll();
        verify(movieDtoConverter).convert(movieList);
    }
}