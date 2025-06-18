package com.proiect.service.impl;

import com.proiect.dto.NewsDto;
import com.proiect.model.EventTypeEnum;
import com.proiect.model.News;
import com.proiect.repository.NewsRepository;
import com.proiect.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public NewsDto getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stirea nu a fost gasita!"));
        return NewsDto.fromNewsToDto(news);
    }

    @Override
    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(NewsDto::fromNewsToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> getRecentNews() {
        return newsRepository.findAll().stream()
                .filter(news -> news.getPublished() != null)
                .sorted((n1, n2) -> n2.getPublished().compareTo(n1.getPublished()))
                .limit(3)
                .map(NewsDto::fromNewsToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<NewsDto> getPaginatedNews(Pageable pageable, String title, EventTypeEnum type) {
        List<NewsDto> all = newsRepository.findAll().stream()
                .filter(n -> {
                    boolean matchTitle = title == null || n.getTitle().toLowerCase().contains(title.toLowerCase());
                    boolean matchType = type == null || n.getEventType().getEventType() == type;
                    return matchTitle && matchType;
                })
                .map(NewsDto::fromNewsToDto)
                .sorted(Comparator.comparing(NewsDto::getPublished).reversed())
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), all.size());
        List<NewsDto> pagedList = all.subList(start, end);
        return new PageImpl<>(pagedList, pageable, all.size());
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
