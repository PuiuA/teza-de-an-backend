package com.proiect.service.impl;

import com.proiect.dto.CategoryResultDto;
import com.proiect.service.CategoryResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryResultServiceImpl implements CategoryResultService {
    @Override
    public List<CategoryResultDto> getAllCategoryResults() {
        return List.of();
    }

    @Override
    public CategoryResultDto getCategoryResultById(Long id) {
        return null;
    }

    @Override
    public CategoryResultDto updateCategoryResult(CategoryResultDto categoryResultDto) {
        return null;
    }

    @Override
    public CategoryResultDto createCategoryResult(CategoryResultDto categoryResultDto) {
        return null;
    }

    @Override
    public void deleteCategoryResultById(Long id) {

    }
}
