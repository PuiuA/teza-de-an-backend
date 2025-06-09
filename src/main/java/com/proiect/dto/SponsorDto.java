package com.proiect.dto;

import com.proiect.model.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SponsorDto {
    private Long id;
    private String title;
    private byte[] image;

    public static SponsorDto fromSponsorToDto(Sponsor sponsor) {
        return SponsorDto.builder()
                .id(sponsor.getId())
                .title(sponsor.getTitle())
                .image(sponsor.getImage())
                .build();
    }
}
