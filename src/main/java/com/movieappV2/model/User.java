package com.movieappV2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    public User(String userName, String password, String mail, boolean isItActive) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.isItActive = isItActive;
    }
}
