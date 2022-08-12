package com.movieappV2.dto.converter;

import com.movieappV2.dto.CategoryDto;
import com.movieappV2.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {

    public CategoryDto convert(Category category) {
        return new CategoryDto(category.getName());
    }

    public List<CategoryDto> convert(List<Category> categories) {
        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}
