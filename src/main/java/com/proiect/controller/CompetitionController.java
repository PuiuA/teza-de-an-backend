package com.proiect.controller;

import com.proiect.dto.CompetitionDto;
import com.proiect.model.Competition;
import com.proiect.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competition")
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping
    public List<CompetitionDto> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/{id}")
    public CompetitionDto getCompetitionById(@PathVariable Long id) {
        return competitionService.getCompetitionById(id);
    }
}
