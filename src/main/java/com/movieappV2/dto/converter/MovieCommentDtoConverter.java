package com.movieappV2.dto.converter;

import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.MovieCommentDto;
import com.movieappV2.dto.MovieDto;
import com.movieappV2.dto.UserDto;
import com.movieappV2.model.MovieComment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieCommentDtoConverter {

    public MovieCommentDto convert(MovieComment movieComment) {
        return new MovieCommentDto(movieComment.getBody(),
                new UserDto(movieComment.getUser().getUserName(), movieComment.getUser().getMail(), movieComment.getUser().isItActive()),
                new MovieDto(movieComment.getMovie().getName(),
                        movieComment.getMovie().getDescription(),
                        movieComment.getMovie().getRating(),
                        movieComment.getMovie().getProducer(),
                        movieComment.getMovie().getReleaseYear(),
                        movieComment.getMovie().getLanguage(),
                        movieComment.getMovie().getSubTitleLanguage(),
                        movieComment.getMovie().getLink(),
                        movieComment.getMovie().getImageUrl(),
                        new CategoryDto(movieComment.getMovie().getCategory().getName())));
    }

    public List<MovieCommentDto> convert(List<MovieComment> movieComments) {
        return movieComments.stream().map(this::convert).collect(Collectors.toList());
    }
}
