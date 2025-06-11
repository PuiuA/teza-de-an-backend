package com.proiect.service.impl;

import com.proiect.dto.CompetitionDto;
import com.proiect.model.Competition;
import com.proiect.repository.CompetitionRepository;
import com.proiect.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public List<CompetitionDto> getAllCompetitions() {
        return competitionRepository.findAll().stream()
                .map(CompetitionDto::fromCompetitionToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompetitionDto getCompetitionById(Long id) {
        return CompetitionDto.fromCompetitionToDto(competitionRepository.findById(id).get());
    }

    @Override
    public List<CompetitionDto> get2Competitions() {
        LocalDateTime today = LocalDateTime.now();

        return competitionRepository.findAll().stream()
                .filter(c -> !c.getDateTime().isBefore(today)) // doar competiții în viitor sau azi
                .sorted(Comparator.comparing(Competition::getDateTime)) // cele mai apropiate
                .limit(2)
                .map(CompetitionDto::fromCompetitionToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public CompetitionDto createCompetition(CompetitionDto competitionDto) {
//        return null;
//    }
//
//    @Override
//    public CompetitionDto updateCompetition(CompetitionDto competitionDto) {
//        return null;
//    }
//
//    @Override
//    public void deleteCompetition(Long id) {
//
//    }
}
