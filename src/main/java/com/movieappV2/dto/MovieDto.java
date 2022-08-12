package com.movieappV2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDto {

    private String name;
    private String description;
    private int rating;
    private String producer;
    private int releaseYear;
    private String language;
    private String subTitleLanguage;
    private String link;
    private CategoryDto categoryDto;
}
