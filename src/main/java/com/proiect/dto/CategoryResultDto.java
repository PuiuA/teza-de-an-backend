package com.proiect.dto;

import com.proiect.model.CategoryResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryResultDto {
    private Long id;

    private CategoryDto category;

    private String competitors;

    private ResultDto result;

    public static CategoryResultDto fromCategoryResultToDto(CategoryResult categoryResult){
        return CategoryResultDto.builder()
                .id(categoryResult.getId())
                .category(CategoryDto.fromCategoryToDto(categoryResult.getCategory()))
                .competitors(categoryResult.getCompetitors())
                .result(ResultDto.fromResultToDtoSimple(categoryResult.getResult()))
                .build();
    }
}
