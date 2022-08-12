package com.movieappV2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;
}
