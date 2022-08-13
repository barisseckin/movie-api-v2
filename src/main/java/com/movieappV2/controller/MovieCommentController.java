package com.movieappV2.controller;

import com.movieappV2.dto.MovieCommentDto;
import com.movieappV2.dto.request.CreateMovieCommentRequest;
import com.movieappV2.dto.request.UpdateMovieCommentRequest;
import com.movieappV2.service.MovieCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/movie-comments")
@RequiredArgsConstructor
@CrossOrigin
public class MovieCommentController {

    private final MovieCommentService movieCommentService;

    @PostMapping
    public ResponseEntity<MovieCommentDto> save(@RequestBody @Valid CreateMovieCommentRequest request) {
        return new ResponseEntity<>(movieCommentService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieCommentDto>> getAll() {
        return new ResponseEntity<>(movieCommentService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{body}")
    public ResponseEntity<?> deleteByBody(@PathVariable("body") String body) {
        movieCommentService.deleteByBody(body);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{body}")
    public ResponseEntity<MovieCommentDto> update(@PathVariable("body") String body, @RequestBody @Valid UpdateMovieCommentRequest request) {
        return new ResponseEntity<>(movieCommentService.update(body, request), HttpStatus.OK);
    }
}
