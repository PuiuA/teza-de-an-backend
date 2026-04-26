package com.proiect.dto;

import com.proiect.model.AthleteResult;
import lombok.*;

@Data
@Builder
public class AthleteResultDto {
    private Long id;
    private Integer place;
    private Long athleteId;
    private String athleteName;
    private String athleteClub;

    public static AthleteResultDto fromEntity(AthleteResult ar) {
        return AthleteResultDto.builder()
                .id(ar.getId())
                .place(ar.getPlace())
                .athleteId(ar.getAthlete().getId())
                .athleteName(ar.getAthlete().getName())
                .athleteClub(ar.getAthlete().getClub())
                .build();
    }
}