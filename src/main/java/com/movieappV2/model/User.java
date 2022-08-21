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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String mail;
    private boolean isItActive;
    private LocalDate createDate;
    private LocalDate updateDate;

    public User(String userName, String password, String mail, boolean isItActive, LocalDate createDate) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.isItActive = isItActive;
        this.createDate = createDate;
    }


}
