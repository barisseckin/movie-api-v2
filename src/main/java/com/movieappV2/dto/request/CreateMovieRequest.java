package com.movieappV2.dto.request;

import lombok.Data;

@Data
public class CreateMovieRequest {

    private String name;
    private String description;
    private String producer;
    private int releaseYear;
    private String language;
    private String subTitleLanguage;
    private String link;
    private String categoryName;

}
