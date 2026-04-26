package com.proiect.dto;

import lombok.*;

@Data
@Builder
public class AthleteCompetitionDto {
    private Integer place;
    private String competitionTitle;
    private String competitionYear;
    private String categoryGender;
    private String categoryKg;
    private Long resultId;
}