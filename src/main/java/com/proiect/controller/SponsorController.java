package com.proiect.controller;

import com.proiect.dto.SponsorDto;
import com.proiect.service.SponsorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sponsors")
public class SponsorController {

    private final SponsorService sponsorService;

    @GetMapping
    public List<SponsorDto> getAll() {
        return sponsorService.getSponsors();
    }

    @GetMapping("/{id}")
    public SponsorDto getById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id);
    }

    @PostMapping
    public SponsorDto create(@RequestBody SponsorDto dto) {
        return sponsorService.createSponsor(dto);
    }

    @PutMapping("/{id}")
    public SponsorDto update(@PathVariable Long id, @RequestBody SponsorDto dto) {
        dto.setId(id);
        return sponsorService.updateSponsor(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sponsorService.deleteSponsor(id);
        return ResponseEntity.noContent().build();
    }
}