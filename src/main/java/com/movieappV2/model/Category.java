package com.movieappV2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate createDate;
    private LocalDate updateDate;

    public Category(String name, LocalDate createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Category(Long id, String name, LocalDate createDate, LocalDate updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Category(Long id, String name, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }
}
