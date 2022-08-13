package com.movieappV2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table
public class MovieComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private LocalDate createDate;
    private LocalDate updateDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

    public MovieComment(String body, LocalDate createDate, User user, Movie movie) {
        this.body = body;
        this.createDate = createDate;
        this.user = user;
        this.movie = movie;
    }
}
