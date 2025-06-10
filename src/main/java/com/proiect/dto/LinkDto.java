package com.proiect.dto;

import com.proiect.model.Competition;
import com.proiect.model.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
                .id(link.getId())
                .url(link.getUrl())
                .description(link.getDescription())
                .build();
    }


}
