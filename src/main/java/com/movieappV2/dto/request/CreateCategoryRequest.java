package com.movieappV2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank
    private String name;
}
