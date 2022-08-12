package com.movieappV2.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String userName;
    private String password;
    private String mail;
}
