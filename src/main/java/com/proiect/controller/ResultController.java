package com.proiect.controller;

import com.proiect.dto.ResultDto;
import com.proiect.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    @GetMapping
    public List<ResultDto> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public ResultDto getResultById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }

    @GetMapping("/years")
    public List<String> getDistinctYears() {
        return resultService.getDistinctYears();
    }

    @GetMapping("/year/{year}")
    public List<ResultDto> getResultsByYear(@PathVariable String year) {
        return resultService.getResultsByYear(year);
    }

    @PostMapping
    public ResultDto createResult(@RequestBody ResultDto dto) {
        return resultService.createResult(dto);
    }

    @PutMapping("/{id}")
    public ResultDto updateResult(@PathVariable Long id, @RequestBody ResultDto dto) {
        dto.setId(id);
        return resultService.updateResult(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}