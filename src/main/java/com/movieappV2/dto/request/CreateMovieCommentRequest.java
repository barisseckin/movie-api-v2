package com.movieappV2.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateMovieCommentRequest {

    @NotBlank
    private String body;
    @Email
    private String userMail;
    @NotBlank
    private String movieName;
}
