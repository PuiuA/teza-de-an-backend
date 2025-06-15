package com.proiect.service.impl;

import com.proiect.dto.ResultDto;
import com.proiect.model.Result;
import com.proiect.repository.ResultRepository;
import com.proiect.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    @Override
    public ResultDto getResultById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result nu a fost gasita!"));
        return ResultDto.fromResultToDto(result);
    }

    @Override
    public List<ResultDto> getAllResults() {
        return resultRepository.findAll().stream()
                .map(ResultDto::fromResultToDto)
                .collect(Collectors.toList());
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
