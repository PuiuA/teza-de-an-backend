package com.proiect.service;

import com.proiect.dto.CompetitionDto;
import com.proiect.model.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {
    public List<CompetitionDto> getAllCompetitions();
    public CompetitionDto getCompetitionById(Long id);
    public List<CompetitionDto> get2Competitions();
    public Page<CompetitionDto> getPaginatedCompetitions(Pageable pageable, String title, Integer year);
    public CompetitionDto createCompetition(CompetitionDto competitionDto);
    public CompetitionDto updateCompetition(Long id, CompetitionDto dto);
    public void deleteCompetition(Long id) ;
}
