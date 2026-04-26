package com.proiect.controller;

import com.proiect.dto.CompetitionDto;
import com.proiect.model.Competition;
import com.proiect.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/recent")
    public List<CompetitionDto> getRecentCompetition() {
        return competitionService.get2Competitions();
    }
    @GetMapping("/paginated")
    public Page<CompetitionDto> getPaginatedCompetitions(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("dateTime").ascending());
        return competitionService.getPaginatedCompetitions(pageable, title, year);
    }
    @PostMapping
    public CompetitionDto createCompetition(@RequestBody CompetitionDto dto) {
        return competitionService.createCompetition(dto);
    }

    @PutMapping("/{id}")
    public CompetitionDto updateCompetition(@PathVariable Long id, @RequestBody CompetitionDto dto) {
        return competitionService.updateCompetition(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }

}
