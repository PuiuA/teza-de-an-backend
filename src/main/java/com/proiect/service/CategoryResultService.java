package com.proiect.service;

import com.proiect.dto.AthleteResultDto;
import com.proiect.dto.CategoryResultDto;

public interface CategoryResultService {
    CategoryResultDto addCategoryToResult(Long resultId, Long categoryId);
    void deleteCategoryResult(Long id);
    AthleteResultDto addAthleteToCategory(Long categoryResultId, Long athleteId, Integer place);
    void removeAthleteFromCategory(Long athleteResultId);
}