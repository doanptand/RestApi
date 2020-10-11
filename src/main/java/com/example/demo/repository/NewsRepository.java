package com.example.demo.repository;

import com.example.demo.entities.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {
    News findNewsByTitle(String title);
}
