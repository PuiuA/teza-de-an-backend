package com.proiect.dto;

import com.proiect.model.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LinkDto {
    private Long id;
    private String url;
    private String description;

    public static LinkDto fromLinkToDto(Link link) {
        return LinkDto.builder()
                .id(link.getLinkId())
                .url(link.getUrl())
                .description(link.getDescription())
                .build();
    }
}
