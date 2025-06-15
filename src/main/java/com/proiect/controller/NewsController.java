package com.proiect.controller;

import com.proiect.dto.NewsDto;
import com.proiect.model.News;
import com.proiect.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public List<NewsDto> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }
    @GetMapping("/paginated")
    public Page<NewsDto> getPaginatedNews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("published").descending());
        return newsService.getPaginatedNews(pageable);
    }

    @GetMapping("/recent")
    public List<NewsDto> getRecentNews() {
        return newsService.getRecentNews();
    }

}
