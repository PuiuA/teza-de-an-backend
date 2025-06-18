package com.proiect.service;

import com.proiect.dto.NewsDto;
import com.proiect.model.EventTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    public NewsDto getNewsById(Long id);
    public List<NewsDto> getAllNews();
    public List<NewsDto> getRecentNews();
    public List<NewsDto> getAllNewsByCategory(String category);
    public List<NewsDto> getAllNewsByTitle(String title);
    public List<NewsDto> getAllNewsByCategoryAndTitle(String category, String title);
    public NewsDto createNews(NewsDto newsDto);
    public NewsDto updateNews(NewsDto newsDto);
    public void deleteNews(Long id);
    public Page<NewsDto> getPaginatedNews(Pageable pageable, String title, EventTypeEnum type);
}
