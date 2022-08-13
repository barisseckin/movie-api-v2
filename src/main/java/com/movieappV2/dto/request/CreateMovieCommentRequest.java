package com.movieappV2.dto.request;

import lombok.Data;

@Data
public class CreateMovieCommentRequest {

    private String body;
    private String userMail;
    private String movieName;
}
