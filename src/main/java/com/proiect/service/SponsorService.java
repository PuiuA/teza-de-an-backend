package com.proiect.service;

import com.proiect.dto.SponsorDto;
import com.proiect.model.Sponsor;

import java.util.List;

public interface SponsorService {
    public SponsorDto getSponsorById(Long id);
    public List<SponsorDto> getSponsors();
    public SponsorDto createSponsor(SponsorDto sponsorDto);
    public SponsorDto updateSponsor(SponsorDto sponsorDto);
    public SponsorDto deleteSponsor(Long id);
}
