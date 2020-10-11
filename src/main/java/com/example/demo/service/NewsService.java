package com.example.demo.service;

import com.example.demo.entities.News;
import com.example.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAllNews() {
        return (List<News>) newsRepository.findAll();
    }

    public News findNewsById(int id) {
        return newsRepository.findById(id).get();
    }

    public News addNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(int id) {
        newsRepository.deleteById(id);
    }

    public News findNewsByTitle(String title) {
        return newsRepository.findNewsByTitle(title);
    }

}
