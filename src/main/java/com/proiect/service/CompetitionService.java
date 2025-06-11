package com.proiect.service;

import com.proiect.dto.CompetitionDto;
import com.proiect.model.Competition;

import java.util.List;

public interface CompetitionService {
    public List<CompetitionDto> getAllCompetitions();
    public CompetitionDto getCompetitionById(Long id);
    public List<CompetitionDto> get2Competitions();
//    public CompetitionDto createCompetition(CompetitionDto competitionDto);
//    public CompetitionDto updateCompetition(CompetitionDto competitionDto);
//    public void deleteCompetition(Long id);
}
