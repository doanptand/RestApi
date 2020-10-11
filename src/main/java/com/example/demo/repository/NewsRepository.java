package com.example.demo.repository;

import com.example.demo.entities.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {
    News findNewsByTitle(String title);

    @Query(value = "SELECT n from News n where n.title=:title")
    News findByTitle(String title);
}
