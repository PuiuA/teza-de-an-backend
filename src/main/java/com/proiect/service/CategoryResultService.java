package com.proiect.service;

import com.proiect.dto.CategoryResultDto;
import com.proiect.model.CategoryResult;

import java.util.List;

public interface CategoryResultService {
    public List<CategoryResultDto> getAllCategoryResults();
    public CategoryResultDto getCategoryResultById(Long id);
    public CategoryResultDto updateCategoryResult(CategoryResultDto categoryResultDto);
    public CategoryResultDto createCategoryResult(CategoryResultDto categoryResultDto);
    public void deleteCategoryResultById(Long id);
}
