package com.proiect.service;

import com.proiect.dto.AthleteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AthleteService {
    List<AthleteDto> getAllAthletes();
    AthleteDto getAthleteById(Long id);
    List<AthleteDto> searchByName(String name);
    AthleteDto createAthlete(AthleteDto dto);
    AthleteDto updateAthlete(Long id, AthleteDto dto);
    void deleteAthlete(Long id);
    Page<AthleteDto> getPaginatedAthletes(Pageable pageable, String name);
}