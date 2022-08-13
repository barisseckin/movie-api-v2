package com.movieappV2.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateMovieCommentRequest {

    @NotBlank
    private String body;
}
