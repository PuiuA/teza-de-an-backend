package com.proiect.service;

import com.proiect.dto.NewsDto;

import java.util.List;

public interface NewsService {
    public NewsDto getNewsById(Long id);
    public List<NewsDto> getAllNews();
    public List<NewsDto> getAllNewsByCategory(String category);
    public List<NewsDto> getAllNewsByTitle(String title);
    public List<NewsDto> getAllNewsByCategoryAndTitle(String category, String title);
    public NewsDto createNews(NewsDto newsDto);
    public NewsDto updateNews(NewsDto newsDto);
    public void deleteNews(Long id);
}
