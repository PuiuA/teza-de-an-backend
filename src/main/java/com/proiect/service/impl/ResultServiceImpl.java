package com.proiect.service.impl;

import com.proiect.dto.ResultDto;
import com.proiect.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    @Override
    public ResultDto getResultById(Long id) {
        return null;
    }

    @Override
    public List<ResultDto> getAllResults() {
        return List.of();
    }

    @Override
    public ResultDto createResult(ResultDto result) {
        return null;
    }

    @Override
    public ResultDto updateResult(ResultDto result) {
        return null;
    }

    @Override
    public ResultDto deleteResult(Long id) {
        return null;
    }
}
