package com.proiect.dto;

import com.proiect.model.CategoryResult;
import lombok.*;

import java.util.List;

@Data
@Builder
public class CategoryResultDto {
    private Long id;
    private CategoryDto category;
    private List<AthleteResultDto> athleteResults;

    public static CategoryResultDto fromEntity(CategoryResult cr) {
        List<AthleteResultDto> athleteDtos = null;
        if (cr.getAthleteResults() != null) {
            athleteDtos = cr.getAthleteResults().stream()
                    .sorted((a, b) -> Integer.compare(a.getPlace(), b.getPlace()))
                    .map(AthleteResultDto::fromEntity)
                    .toList();
        }
        return CategoryResultDto.builder()
                .id(cr.getId())
                .category(CategoryDto.fromEntity(cr.getCategory()))
                .athleteResults(athleteDtos)
                .build();
    }
}