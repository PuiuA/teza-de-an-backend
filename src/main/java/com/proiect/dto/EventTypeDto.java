package com.proiect.dto;

import com.proiect.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventTypeDto {
    private Long id;
    private String name;

    public static EventTypeDto fromEventTypeToDto(EventType eventType) {
        return EventTypeDto.builder()
                .id(eventType.getId())
                .name(eventType.getName())
                .build();
    }


}
