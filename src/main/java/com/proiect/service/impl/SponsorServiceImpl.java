package com.proiect.service.impl;

import com.proiect.dto.SponsorDto;
import com.proiect.service.SponsorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorServiceImpl implements SponsorService {
    @Override
    public SponsorDto getSponsorById(Long id) {
        return null;
    }

    @Override
    public List<SponsorDto> getSponsors() {
        return List.of();
    }

    @Override
    public SponsorDto createSponsor(SponsorDto sponsorDto) {
        return null;
    }

    @Override
    public SponsorDto updateSponsor(SponsorDto sponsorDto) {
        return null;
    }

    @Override
    public SponsorDto deleteSponsor(Long id) {
        return null;
    }
}
