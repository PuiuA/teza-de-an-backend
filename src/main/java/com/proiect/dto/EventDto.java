package com.proiect.dto;

import com.proiect.model.Category;
import com.proiect.model.Event;
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
public class EventDto {
    private Long id;
    private String title;
    private String description;
    private String shortDescription;
    private LocalDateTime published;
    private EventTypeDto eventType;
    private byte[] image;
    private List<String> links;
    private String author;
    private List<CategoryDto> categories;
    private String information;

    public static EventDto fromEventToDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .shortDescription(event.getShortDescription())
                .published(event.getPublished())
                .eventType(EventTypeDto.fromEventTypeToDto(event.getEventType()))
                .image(event.getImage())
                .links(event.getLinks())
                .author(event.getAuthor())
                .categories(mapCategoriesToCategoryDtoList(event))
                .information(event.getInformation())
                .build();
    }

    private static List<CategoryDto> mapCategoriesToCategoryDtoList(Event event) {
        if (event.getCategories() != null) {
            return event.getCategories().stream()
                    .map(CategoryDto::fromCategoryToDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
