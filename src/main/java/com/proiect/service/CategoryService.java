package com.proiect.service;

import com.proiect.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    public List<CategoryDto> getAllCategories();
    public CategoryDto getCategoryById(Long id);
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto);
    public void deleteCategory(Long id);
}
