package com.proiect.dto;

import com.proiect.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDto {
    private Long id;

    private String gender;

    private String kilograms;

    public static CategoryDto fromCategoryToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .gender(category.getGender())
                .kilograms(category.getKilograms())
                .build();
    }
}
