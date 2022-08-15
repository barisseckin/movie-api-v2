package com.movieappV2.service;

import com.movieappV2.TestUtils;
import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.converter.CategoryDtoConverter;
import com.movieappV2.dto.request.CreateCategoryRequest;
import com.movieappV2.model.Category;
import com.movieappV2.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest extends TestUtils {

    private CategoryRepository categoryRepository;
    private CategoryDtoConverter categoryDtoConverter;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryDtoConverter = mock(CategoryDtoConverter.class);

        categoryService = new CategoryService(categoryRepository, categoryDtoConverter);
    }

    @Test
    public void testGetAll_itShouldReturnCategoryDtoList() {
        List<Category> categoryList = generateCategoryList();
        List<CategoryDto> categoryDtoList = generateCategoryDtoList(categoryList);

        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryDtoConverter.convert(categoryList)).thenReturn(categoryDtoList);

        List<CategoryDto> result = categoryService.getAll();

        assertEquals(result, categoryDtoList);
        verify(categoryRepository).findAll();
        verify(categoryDtoConverter).convert(categoryList);
    }


}