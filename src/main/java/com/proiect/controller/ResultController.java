package com.proiect.controller;

import com.proiect.dto.ResultDto;
import com.proiect.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("{id}")
    public ResultDto getResultById(@PathVariable Long id) {
        return resultService.getResultById(id);
    }
}
