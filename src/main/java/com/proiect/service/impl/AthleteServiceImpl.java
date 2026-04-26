package com.proiect.service.impl;

import com.proiect.dto.AthleteDto;
import com.proiect.model.Athlete;
import com.proiect.repository.AthleteRepository;
import com.proiect.repository.AthleteResultRepository;
import com.proiect.service.AthleteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteResultRepository athleteResultRepository;
    private final AthleteRepository athleteRepository;

    @Override
    public List<AthleteDto> getAllAthletes() {
        return athleteRepository.findAll().stream()
                .map(AthleteDto::fromEntity)
                .toList();
    }

    @Override
    public AthleteDto getAthleteById(Long id) {
        Athlete a = athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sportivul nu a fost gasit!"));
        return AthleteDto.fromEntityWithResults(a);
    }

    @Override
    public List<AthleteDto> searchByName(String name) {
        return athleteRepository.findByNameContainingIgnoreCase(name).stream()
                .map(AthleteDto::fromEntity)
                .toList();
    }

    @Override
    public AthleteDto createAthlete(AthleteDto dto) {
        Athlete a = Athlete.builder()
                .name(dto.getName())
                .club(dto.getClub())
                .birthYear(dto.getBirthYear())
                .weightKg(dto.getWeightKg())
                .belt(dto.getBelt())
                .photo(dto.getPhoto())
                .build();
        return AthleteDto.fromEntity(athleteRepository.save(a));
    }

    @Override
    public AthleteDto updateAthlete(Long id, AthleteDto dto) {
        Athlete a = athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sportivul nu a fost găsit!"));
        a.setName(dto.getName());
        a.setClub(dto.getClub());
        a.setBirthYear(dto.getBirthYear());
        a.setWeightKg(dto.getWeightKg());
        a.setBelt(dto.getBelt());
        if (dto.getPhoto() != null) a.setPhoto(dto.getPhoto());
        return AthleteDto.fromEntity(athleteRepository.save(a));
    }

    @Override
    @Transactional
    public void deleteAthlete(Long id) {
        athleteResultRepository.deleteByAthleteId(id);
        athleteRepository.deleteById(id);
    }

    @Override
    public Page<AthleteDto> getPaginatedAthletes(Pageable pageable, String name) {
        List<AthleteDto> all = athleteRepository.findAll().stream()
                .filter(a -> name == null || a.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted(Comparator.comparing(Athlete::getName))
                .map(AthleteDto::fromEntity)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), all.size());
        return new PageImpl<>(all.subList(start, end), pageable, all.size());
    }
}