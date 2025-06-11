package com.proiect.controller;

import com.proiect.dto.NewsDto;
import com.proiect.model.News;
import com.proiect.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
