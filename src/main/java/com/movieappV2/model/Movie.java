package com.movieappV2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int rating;
    private String producer;
    private int releaseYear;
    private String language;
    private String subTitleLanguage;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String link;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;


    public Movie(String name, String description, String producer, int releaseYear, String language, String subTitleLanguage,
                 LocalDate createDate, String link, String imageUrl, Category category) {

        this.name = name;
        this.description = description;
        this.producer = producer;
        this.releaseYear = releaseYear;
        this.language = language;
        this.subTitleLanguage = subTitleLanguage;
        this.createDate = createDate;
        this.link = link;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
