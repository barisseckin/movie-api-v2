package com.movieappV2.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateMovieRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String producer;
    @NotNull
    private int releaseYear;
    @NotBlank
    private String language;
    @NotBlank
    private String subTitleLanguage;
    @NotBlank
    private String link;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String categoryName;

}
