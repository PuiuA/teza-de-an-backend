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
                .orElseThrow(() -> new RuntimeException("Result nu a fost gasit!"));
        return ResultDto.fromResultToDto(result);
    }

    @Override
    public List<ResultDto> getAllResults() {
        return resultRepository.findAll().stream()
                .map(ResultDto::fromResultToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDto> getResultsByYear(String year) {
        return resultRepository.findByYear(year).stream()
                .map(ResultDto::fromResultToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDistinctYears() {
        return resultRepository.findDistinctYears();
    }

    @Override
    public ResultDto createResult(ResultDto dto) {
        Result result = Result.builder()
                .title(dto.getTitle())
                .year(dto.getYear())
                .age(dto.getAge())
                .build();
        return ResultDto.fromResultToDto(resultRepository.save(result));
    }

    @Override
    public ResultDto updateResult(ResultDto dto) {
        Result result = resultRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Result negasit"));
        result.setTitle(dto.getTitle());
        result.setYear(dto.getYear());
        result.setAge(dto.getAge());
        return ResultDto.fromResultToDto(resultRepository.save(result));
    }

    @Override
    public ResultDto deleteResult(Long id) {
        resultRepository.deleteById(id);
        return null;
    }
}