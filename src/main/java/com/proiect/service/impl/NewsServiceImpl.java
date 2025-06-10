package com.proiect.service.impl;

import com.proiect.dto.NewsDto;
import com.proiect.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    @Override
    public NewsDto getNewsById(Long id) {
        return null;
    }

    @Override
    public List<NewsDto> getAllNews() {
        return List.of();
    }

    @Override
    public List<NewsDto> getAllNewsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<NewsDto> getAllNewsByTitle(String title) {
        return List.of();
    }

    @Override
    public List<NewsDto> getAllNewsByCategoryAndTitle(String category, String title) {
        return List.of();
    }

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        return null;
    }

    @Override
    public NewsDto updateNews(NewsDto newsDto) {
        return null;
    }

    @Override
    public void deleteNews(Long id) {

    }
}
