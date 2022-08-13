package com.movieappV2.controller;

import com.movieappV2.dto.CategoryDto;
import com.movieappV2.dto.request.CreateCategoryRequest;
import com.movieappV2.dto.request.UpdateCategoryRequest;
import com.movieappV2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CreateCategoryRequest request) {
        return new ResponseEntity<>(categoryService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<CategoryDto> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(categoryService.getByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable("name") String name) {
        categoryService.deleteByName(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<CategoryDto> update(@PathVariable("name") String name, @RequestBody @Valid UpdateCategoryRequest request) {
        return new ResponseEntity<>(categoryService.update(name, request), HttpStatus.OK);
    }
}
