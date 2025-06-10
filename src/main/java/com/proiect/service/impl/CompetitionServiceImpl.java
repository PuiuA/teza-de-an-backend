package com.proiect.service.impl;

import com.proiect.dto.CompetitionDto;
import com.proiect.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    @Override
    public List<CompetitionDto> getAllCompetitions() {
        return List.of();
    }

    @Override
    public CompetitionDto getCompetitionById(Long id) {
        return null;
    }

    @Override
    public CompetitionDto createCompetition(CompetitionDto competitionDto) {
        return null;
    }

    @Override
    public CompetitionDto updateCompetition(CompetitionDto competitionDto) {
        return null;
    }

    @Override
    public void deleteCompetition(Long id) {

    }
}
