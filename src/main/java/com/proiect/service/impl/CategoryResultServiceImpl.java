package com.proiect.service.impl;

import com.proiect.dto.AthleteResultDto;
import com.proiect.dto.CategoryResultDto;
import com.proiect.model.*;
import com.proiect.repository.*;
import com.proiect.service.CategoryResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryResultServiceImpl implements CategoryResultService {

    private final CategoryResultRepository categoryResultRepository;
    private final CategoryRepository categoryRepository;
    private final ResultRepository resultRepository;
    private final AthleteRepository athleteRepository;
    private final AthleteResultRepository athleteResultRepository;

    @Override
    public CategoryResultDto addCategoryToResult(Long resultId, Long categoryId) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result negăsit"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category negăsită"));

        CategoryResult cr = CategoryResult.builder()
                .result(result)
                .category(category)
                .build();
        return CategoryResultDto.fromEntity(categoryResultRepository.save(cr));
    }

    @Override
    public void deleteCategoryResult(Long id) {
        categoryResultRepository.deleteById(id);
    }

    @Override
    public AthleteResultDto addAthleteToCategory(Long categoryResultId,
                                                 Long athleteId, Integer place) {
        CategoryResult cr = categoryResultRepository.findById(categoryResultId)
                .orElseThrow(() -> new RuntimeException("CategoryResult negăsit"));
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Sportiv negăsit"));

        AthleteResult ar = AthleteResult.builder()
                .categoryResult(cr)
                .athlete(athlete)
                .place(place)
                .build();
        return AthleteResultDto.fromEntity(athleteResultRepository.save(ar));
    }

    @Override
    public void removeAthleteFromCategory(Long athleteResultId) {
        athleteResultRepository.deleteById(athleteResultId);
    }
}