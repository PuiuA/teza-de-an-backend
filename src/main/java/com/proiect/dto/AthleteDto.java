package com.proiect.dto;

import com.proiect.model.Athlete;
import lombok.*;

import java.util.List;

@Data
@Builder
public class AthleteDto {
    private Long id;
    private String name;
    private String club;
    private Integer birthYear;
    private Double weightKg;
    private String belt;
    private byte[] photo;
    private List<AthleteCompetitionDto> competitionResults;

    public static AthleteDto fromEntity(Athlete a) {
        return AthleteDto.builder()
                .id(a.getId())
                .name(a.getName())
                .club(a.getClub())
                .birthYear(a.getBirthYear())
                .weightKg(a.getWeightKg())
                .belt(a.getBelt())
                .photo(a.getPhoto())
                .build();
    }

    public static AthleteDto fromEntityWithResults(Athlete a) {
        List<AthleteCompetitionDto> compResults = null;
        if (a.getResults() != null) {
            compResults = a.getResults().stream()
                    .map(ar -> AthleteCompetitionDto.builder()
                            .place(ar.getPlace())
                            .competitionTitle(ar.getCategoryResult().getResult().getTitle())
                            .competitionYear(ar.getCategoryResult().getResult().getYear())
                            .categoryGender(ar.getCategoryResult().getCategory().getGender())
                            .categoryKg(ar.getCategoryResult().getCategory().getKilograms())
                            .resultId(ar.getCategoryResult().getResult().getId())
                            .build())
                    .toList();
        }
        return AthleteDto.builder()
                .id(a.getId())
                .name(a.getName())
                .club(a.getClub())
                .birthYear(a.getBirthYear())
                .weightKg(a.getWeightKg())
                .belt(a.getBelt())
                .photo(a.getPhoto())
                .competitionResults(compResults)
                .build();
    }
}