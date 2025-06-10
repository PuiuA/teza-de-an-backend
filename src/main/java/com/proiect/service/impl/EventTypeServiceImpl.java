package com.proiect.service.impl;

import com.proiect.dto.EventTypeDto;
import com.proiect.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {
    @Override
    public List<EventTypeDto> getAllEventTypes() {
        return List.of();
    }

    @Override
    public EventTypeDto getEventTypeById(Long id) {
        return null;
    }
}
