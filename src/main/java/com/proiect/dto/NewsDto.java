package com.proiect.dto;

import com.proiect.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewsDto {
    private Long id;
    private String title;
    private String description;
    private String shortDescription;
    private LocalDateTime published;
    private EventTypeDto eventType;
    private byte[] image;
    private List<LinkDto> links;
    private String author;
    private String information;

    public static NewsDto fromEventToDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .shortDescription(news.getShortDescription())
                .published(news.getPublished())
                .eventType(EventTypeDto.fromEventTypeToDto(news.getEventType()))
                .image(news.getImage())
                .links(mapLinkToLinkDtoList(news))
                .author(news.getAuthor())
                .information(news.getInformation())
                .build();
    }

    private static List<LinkDto> mapLinkToLinkDtoList(News news) {
        if (news.getLinks() != null) {
            return news.getLinks().stream()
                    .map(LinkDto::fromLinkToDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
