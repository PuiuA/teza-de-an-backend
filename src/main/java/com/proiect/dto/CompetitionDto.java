package com.proiect.dto;

import com.proiect.model.Competition;
import com.proiect.model.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompetitionDto {

    private Long id;
    private String title;
    private String description;
    private String shortDescription;
    private byte[] image;
    private LocalDateTime date;
    private List<LinkDto> links;
    private String information;
    private ResultDto result;

    public static CompetitionDto fromCompetitionToDto(Competition competition) {
        return CompetitionDto.builder()
                .id(competition.getId())
                .title(competition.getTitle())
                .description(competition.getDescription())
                .shortDescription(competition.getShortDescription())
                .image(competition.getImage())
                .date(competition.getDateTime())
                .information(competition.getInformation())
                .links(competition.getLinks().stream()
                        .map(LinkDto::fromLinkToDto)
                        .collect(Collectors.toList()))
                .result(competition.getResult() != null
                        ? ResultDto.fromResultToDtoSimple(competition.getResult())
                        : null)
                .build();
    }

    public static CompetitionDto fromCompetitionToDtoSimple(Competition competition) {
        return CompetitionDto.builder()
                .id(competition.getId())
                .title(competition.getTitle())
                .description(competition.getDescription())
                .shortDescription(competition.getShortDescription())
                .image(competition.getImage())
                .date(competition.getDateTime())
                .information(competition.getInformation())
                .links(competition.getLinks().stream()
                        .map(LinkDto::fromLinkToDto)
                        .collect(Collectors.toList()))
                .build();
    }

}
