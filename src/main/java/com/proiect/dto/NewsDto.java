package com.proiect.dto;

import com.proiect.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewsDto {
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private byte[] image;
    private LocalDateTime published;

    public static NewsDto fromNewsToDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .shortDescription(news.getShortDescription())
                .description(news.getDescription())
                .image(news.getImage())
                .published(news.getPublished())
                .build();
    }

}
