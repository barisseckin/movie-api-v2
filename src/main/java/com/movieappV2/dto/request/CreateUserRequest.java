package com.movieappV2.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @Email
    private String mail;
}
