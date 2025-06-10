package com.proiect.service;

import com.proiect.dto.EventTypeDto;

import java.util.List;

public interface EventTypeService {
    public List<EventTypeDto> getAllEventTypes();
    public EventTypeDto getEventTypeById(Long id);
}
