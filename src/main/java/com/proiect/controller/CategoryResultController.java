package com.proiect.controller;

import com.proiect.dto.AddAthleteRequest;
import com.proiect.dto.AddCategoryRequest;
import com.proiect.dto.CategoryResultDto;
import com.proiect.dto.AthleteResultDto;
import com.proiect.service.CategoryResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category-result")
public class CategoryResultController {

    private final CategoryResultService categoryResultService;

    @PostMapping
    public CategoryResultDto addCategory(@RequestBody AddCategoryRequest request) {
        return categoryResultService.addCategoryToResult(
                request.getResultId(), request.getCategoryId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryResultService.deleteCategoryResult(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{categoryResultId}/athlete")
    public AthleteResultDto addAthlete(
            @PathVariable Long categoryResultId,
            @RequestBody AddAthleteRequest request) {
        return categoryResultService.addAthleteToCategory(
                categoryResultId, request.getAthleteId(), request.getPlace());
    }

    @DeleteMapping("/athlete/{athleteResultId}")
    public ResponseEntity<Void> removeAthlete(@PathVariable Long athleteResultId) {
        categoryResultService.removeAthleteFromCategory(athleteResultId);
        return ResponseEntity.noContent().build();
    }
}