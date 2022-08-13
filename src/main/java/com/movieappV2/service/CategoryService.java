package com.movieappV2.service;

import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.converter.CategoryDtoConverter;
import com.movieappV2.dto.request.CreateCategoryRequest;
import com.movieappV2.dto.request.UpdateCategoryRequest;
import com.movieappV2.dto.request.UpdateMovieRequest;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.Category;
import com.movieappV2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryDto save(CreateCategoryRequest request) {
        Category category = new Category(request.getName(),
                LocalDate.now());

        return categoryDtoConverter.convert(categoryRepository.save(category));
    }

    public List<CategoryDto> getAll() {
        return categoryDtoConverter.convert(categoryRepository.findAll());
    }

    public CategoryDto getByName(String name) {
        Category category = categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new NotFoundException("Category not found, category name: " + name));

        return categoryDtoConverter.convert(category);
    }

    public void deleteByName(String name) {
        Category category = categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new NotFoundException("Category not found, category name: " + name));

        categoryRepository.deleteById(category.getId());
    }

    public CategoryDto update(String name, UpdateCategoryRequest request) {
        Category category = getCategoryByName(name);

        category.setName(request.getName());
        category.setUpdateDate(LocalDate.now());

        return categoryDtoConverter.convert(categoryRepository.save(category));
    }

    protected Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new NotFoundException("category not found, category name: " + name));
    }

}
