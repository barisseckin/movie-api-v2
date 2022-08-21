package com.movieappV2;

import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.MovieDto;
import com.movieappV2.model.Category;
import com.movieappV2.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class TestUtils {

    public static List<Category> generateCategoryList() {
        return LongStream.range(0, 5).mapToObj(i ->
                new Category(i, i + "Test-Name", LocalDate.now(), LocalDate.now())).collect(Collectors.toList());
    }

    public static List<CategoryDto> generateCategoryDtoList(List<Category> categories) {
        return categories.stream().map(category -> new CategoryDto(category.getName())).collect(Collectors.toList());
    }

    public static Category generateCategory(Long id, String name, LocalDate createDate, LocalDate updateDate) {
        return new Category(id,
                name,
                createDate,
                updateDate);
    }

    public static CategoryDto generateCategoryDto(String name) {
        return new CategoryDto(name);
    }

    public static List<Movie> generateMovieList() {
        return LongStream.range(0, 5).mapToObj(i ->
                new Movie(i,  "Test-Name", i + "Test-Description",  5, i + "Test-Producer",
                        2022, "Test-Language",
                        "Test-SubTitleLanguage", LocalDate.now(), LocalDate.now(), "Test-Link", "Test-ImageUrl",
                        new Category(i, "Test-Name", LocalDate.now(), LocalDate.now()))).collect(Collectors.toList());
    }

    public static List<MovieDto> generateMovieDtoList(List<Movie> movieList) {
        return movieList.stream().map(movie -> new MovieDto(movie.getName(), movie.getDescription(), movie.getRating(),
                movie.getProducer(), movie.getReleaseYear(), movie.getLanguage(), movie.getSubTitleLanguage(),
                movie.getLink(), movie.getImageUrl(), new CategoryDto(movie.getCategory().getName()))).collect(Collectors.toList());
    }
}
