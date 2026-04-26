package com.proiect.service.impl;

import com.proiect.dto.CompetitionDto;
import com.proiect.model.Competition;
import com.proiect.repository.CompetitionRepository;
import com.proiect.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
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
                .filter(c -> c.getDateTime() != null) //null check
                .filter(c -> !c.getDateTime().isBefore(today))
                .sorted(Comparator.comparing(Competition::getDateTime))
                .limit(2)
                .map(CompetitionDto::fromCompetitionToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CompetitionDto> getPaginatedCompetitions(Pageable pageable, String title, Integer year) {
        List<CompetitionDto> all = competitionRepository.findAll().stream()
                .filter(c -> {
                    boolean matchTitle = title == null || c.getTitle().toLowerCase().contains(title.toLowerCase());
                    boolean matchYear = year == null ||
                            (c.getDateTime() != null && c.getDateTime().getYear() == year);
                    return matchTitle && matchYear;
                })
                .map(CompetitionDto::fromCompetitionToDto)
                .filter(dto -> dto.getDate() != null) //  null check pe DTO pentru sort
                .sorted(Comparator.comparing(CompetitionDto::getDate))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), all.size());
        return new PageImpl<>(all.subList(start, end), pageable, all.size());
    }

    @Override
    public CompetitionDto createCompetition(CompetitionDto dto) {
        Competition c = Competition.builder()
                .title(dto.getTitle())
                .shortDescription(dto.getShortDescription())
                .description(dto.getDescription())
                .information(dto.getInformation())
                .dateTime(dto.getDate())
                .image(dto.getImage())
                .links(List.of())
                .build();
        return CompetitionDto.fromCompetitionToDtoSimple(competitionRepository.save(c));
    }

    @Override
    public CompetitionDto updateCompetition(Long id, CompetitionDto dto) {
        Competition c = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competiție negăsită"));
        c.setTitle(dto.getTitle());
        c.setShortDescription(dto.getShortDescription());
        c.setDescription(dto.getDescription());
        c.setInformation(dto.getInformation());
        c.setDateTime(dto.getDate());
        if (dto.getImage() != null) c.setImage(dto.getImage());
        return CompetitionDto.fromCompetitionToDtoSimple(competitionRepository.save(c));
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }
}
