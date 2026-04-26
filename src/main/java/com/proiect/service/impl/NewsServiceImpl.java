package com.proiect.service.impl;

import com.proiect.dto.NewsDto;
import com.proiect.model.EventTypeEnum;
import com.proiect.model.News;
import com.proiect.repository.EventTypeRepository;
import com.proiect.repository.NewsRepository;
import com.proiect.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final EventTypeRepository eventTypeRepository;

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
    public NewsDto createNews(NewsDto dto) {
        News news = News.builder()
                .title(dto.getTitle())
                .shortDescription(dto.getShortDescription())
                .description(dto.getDescription())
                .author(dto.getAuthor())
                .information(dto.getInformation())
                .published(dto.getPublished() != null ? dto.getPublished() : LocalDateTime.now())
                .eventType(eventTypeRepository.findById(
                                dto.getEventType() != null ? dto.getEventType().getId() : 1L)
                        .orElseThrow())
                .image(dto.getImage())
                .links(List.of())
                .build();
        return NewsDto.fromNewsToDto(newsRepository.save(news));
    }

    @Override
    public NewsDto updateNews(NewsDto dto) {
        News news = newsRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Știrea nu a fost gasită"));
        news.setTitle(dto.getTitle());
        news.setShortDescription(dto.getShortDescription());
        news.setDescription(dto.getDescription());
        news.setAuthor(dto.getAuthor());
        news.setInformation(dto.getInformation());
        if (dto.getPublished() != null) news.setPublished(dto.getPublished());
        if (dto.getImage() != null) news.setImage(dto.getImage());
        return NewsDto.fromNewsToDto(newsRepository.save(news));
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
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

}

