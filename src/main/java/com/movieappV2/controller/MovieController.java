package com.movieappV2.controller;

import com.movieappV2.dto.MovieDto;
import com.movieappV2.dto.request.CreateMovieRequest;
import com.movieappV2.dto.request.UpdateMovieRequest;
import com.movieappV2.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> save(@RequestBody @Valid CreateMovieRequest request) {
        return new ResponseEntity<>(movieService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-categoryName/{categoryName}")
    public ResponseEntity<List<MovieDto>> getByCategoryName(@PathVariable("categoryName") String categoryName) {
        return new ResponseEntity<>(movieService.getByCategoryName(categoryName), HttpStatus.OK);
    }

    @GetMapping("/get-by-rating/{rating}")
    public ResponseEntity<List<MovieDto>> getByRating(@PathVariable("rating") int rating) {
        return new ResponseEntity<>(movieService.getByRating(rating), HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<MovieDto> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(movieService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/search-by-name/{name}")
    public ResponseEntity<List<MovieDto>> searchByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(movieService.searchByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable("name") String name) {
        movieService.deleteByName(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<MovieDto> update(@PathVariable("name") String name, @RequestBody @Valid UpdateMovieRequest request) {
        return new ResponseEntity<>(movieService.update(name, request), HttpStatus.OK);
    }
}
