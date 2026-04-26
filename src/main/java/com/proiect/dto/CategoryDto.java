package com.proiect.dto;

import com.proiect.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String gender;
    private String kilograms;

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .gender(category.getGender())
                .kilograms(category.getKilograms())
                .build();
    }
}