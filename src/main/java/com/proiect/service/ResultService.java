package com.proiect.service;

import com.proiect.dto.ResultDto;

import java.util.List;

public interface ResultService {
    public ResultDto getResultById(Long id);
    public List<ResultDto> getAllResults();
    public ResultDto createResult(ResultDto result);
    public ResultDto updateResult(ResultDto result);
    public ResultDto deleteResult(Long id);
    List<ResultDto> getResultsByYear(String year);
    List<String> getDistinctYears();
}
