package com.proiect.service.impl;

import com.proiect.dto.SponsorDto;
import com.proiect.model.Sponsor;
import com.proiect.repository.SponsorRepository;
import com.proiect.service.SponsorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;

    @Override
    public SponsorDto getSponsorById(Long id) {
        return sponsorRepository.findById(id)
                .map(SponsorDto::fromSponsorToDto)
                .orElseThrow(() -> new RuntimeException("Sponsor negăsit"));
    }

    @Override
    public List<SponsorDto> getSponsors() {
        return sponsorRepository.findAll().stream()
                .map(SponsorDto::fromSponsorToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SponsorDto createSponsor(SponsorDto dto) {
        Sponsor sponsor = Sponsor.builder()
                .title(dto.getTitle())
                .link(dto.getLink())
                .image(dto.getImage())
                .build();
        return SponsorDto.fromSponsorToDto(sponsorRepository.save(sponsor));
    }

    @Override
    public SponsorDto updateSponsor(SponsorDto dto) {
        Sponsor sponsor = sponsorRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Sponsor negăsit"));
        sponsor.setTitle(dto.getTitle());
        sponsor.setLink(dto.getLink());
        if (dto.getImage() != null) sponsor.setImage(dto.getImage());
        return SponsorDto.fromSponsorToDto(sponsorRepository.save(sponsor));
    }

    @Override
    public SponsorDto deleteSponsor(Long id) {
        sponsorRepository.deleteById(id);
        return null;
    }
}