package com.proiect.controller;

import com.proiect.dto.AthleteDto;
import com.proiect.service.AthleteService;
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
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    @GetMapping
    public List<AthleteDto> getAll() {
        return athleteService.getAllAthletes();
    }

    @GetMapping("/{id}")
    public AthleteDto getById(@PathVariable Long id) {
        return athleteService.getAthleteById(id);
    }

    @GetMapping("/search")
    public List<AthleteDto> search(@RequestParam String name) {
        return athleteService.searchByName(name);
    }

    @PostMapping
    public AthleteDto create(@RequestBody AthleteDto dto) {
        return athleteService.createAthlete(dto);
    }

    @PutMapping("/{id}")
    public AthleteDto update(@PathVariable Long id, @RequestBody AthleteDto dto) {
        return athleteService.updateAthlete(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/paginated")
    public Page<AthleteDto> getPaginated(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return athleteService.getPaginatedAthletes(pageable, name);
    }
}