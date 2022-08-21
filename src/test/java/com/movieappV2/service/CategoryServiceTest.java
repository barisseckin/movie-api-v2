package com.movieappV2.service;

import com.movieappV2.TestUtils;
import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.converter.CategoryDtoConverter;
import com.movieappV2.exception.NotFoundException;
import com.movieappV2.model.Category;
import com.movieappV2.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testGetByName_itShouldReturnCategoryDto() {
        CategoryDto categoryDto = generateCategoryDto("Test-Name");
        Category category = generateCategory(1L, "Test-Name", LocalDate.now(), LocalDate.now());

        when(categoryRepository.findCategoryByName("Test-Name")).thenReturn(Optional.of(category));
        when(categoryDtoConverter.convert(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.getByName("Test-Name");

        assertEquals(result, categoryDto);
        verify(categoryRepository).findCategoryByName("Test-Name");
        verify(categoryDtoConverter).convert(category);
    }

    @Test
    public void testGetCategoryByName_itShouldReturnCategory() {
        Category category = generateCategory(1L, "Test-Name", LocalDate.now(), LocalDate.now());

        when(categoryRepository.findCategoryByName("Test-Name")).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryByName("Test-Name");

        assertEquals(result, category);
        verify(categoryRepository).findCategoryByName("Test-Name");
    }

    @Test
    public void testGetCategoryByName_whenCategoryNameDoesNotExist_shouldThrowNotFoundException() {

        when(categoryRepository.findCategoryByName("Test-Name")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryService.getByName("Test-Name"));

        verify(categoryRepository).findCategoryByName("Test-Name");
    }

}