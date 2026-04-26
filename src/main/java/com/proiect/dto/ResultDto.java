package com.proiect.dto;

import com.proiect.model.CategoryResult;
import com.proiect.model.Result;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultDto {
    private Long id;
    private String title;
    private String year;
    private int age;
    private List<CategoryResultDto> categoryResults;

    public static ResultDto fromResultToDto(Result result) {
        List<CategoryResultDto> catDtos = null;
        if (result.getCategoryResults() != null) {
            catDtos = result.getCategoryResults().stream()
                    .map(CategoryResultDto::fromEntity)
                    .toList();
        }
        return ResultDto.builder()
                .id(result.getId())
                .title(result.getTitle())
                .year(result.getYear())
                .age(result.getAge())
                .categoryResults(catDtos)
                .build();
    }
}