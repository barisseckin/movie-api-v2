package com.movieappV2.service;

import com.movieappV2.dto.MovieCommentDto;
import com.movieappV2.dto.converter.MovieCommentDtoConverter;
import com.movieappV2.dto.request.CreateMovieCommentRequest;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.Movie;
import com.movieappV2.model.MovieComment;
import com.movieappV2.model.User;
import com.movieappV2.repository.MovieCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieCommentService {

    private final MovieCommentRepository movieCommentRepository;
    private final MovieCommentDtoConverter movieCommentDtoConverter;
    private final MovieService movieService;
    private final UserService userService;

    public MovieCommentDto save(CreateMovieCommentRequest request) {
        Movie movie = movieService.getMovieByName(request.getMovieName());
        User user = userService.getUserByMail(request.getUserMail());

        MovieComment movieComment = new MovieComment(request.getBody(),
                LocalDate.now(),
                user,
                movie);

        if (user.isItActive()) {
            return movieCommentDtoConverter.convert(movieCommentRepository.save(movieComment));
        }
        else {
            return null;
        }
    }

    public List<MovieCommentDto> getAll() {
        return movieCommentDtoConverter.convert(movieCommentRepository.findAll());
    }

    public void deleteByBody(String body) {
        MovieComment movieComment = movieCommentRepository.findByBody(body).orElseThrow(() -> new NotFoundException("comment not found, comment body: " + body));
        movieCommentRepository.deleteById(movieComment.getId());
    }

}
