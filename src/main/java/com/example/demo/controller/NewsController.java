package com.example.demo.controller;

import com.example.demo.entities.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/all")
    public List<News> getAllNews() {
        return newsService.findAllNews();
    }

    @GetMapping("/one/{title}")
    public News getNews(@PathVariable String title) {
        return newsService.findNewsByTitle(title);
    }

    @PostMapping("/add")
    public News addNews(@RequestBody News news) {
        return newsService.addNews(news);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNews(@PathVariable int id) {
        newsService.deleteNews(id);
    }

}
