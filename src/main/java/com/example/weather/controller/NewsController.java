package com.example.weather.controller;

import com.example.weather.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/news")
    public String getNewsPage(Model model, @PageableDefault(size = 6) Pageable pageable) throws IOException {
        model.addAttribute("news", newsService.getNews(pageable));
        return "news";
    }
}
