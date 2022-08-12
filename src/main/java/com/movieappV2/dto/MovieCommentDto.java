package com.movieappV2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieCommentDto {

    private String body;
    private UserDto userDto;
    private MovieDto movieDto;
}
