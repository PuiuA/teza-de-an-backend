package com.proiect.dto;

import com.proiect.model.CategoryResult;
import com.proiect.model.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultDto {
    private Long id;
    private String title;
    private String year;
    private int age;
    private List<CategoryResultDto> categoryResults;

    public static ResultDto fromResultToDto(Result result) {
        return ResultDto.builder()
                .id(result.getId())
                .title(result.getTitle())
                .year(result.getYear())
                .age(result.getAge())
                .categoryResults(result.getCategoryResults().stream()
                        .map(CategoryResultDto::fromCategoryResultToDto)
                        .collect(Collectors.toList()))
                .build();
    }

}
