package com.example.demo.repository;

import com.example.demo.entities.User;

import java.util.List;

public interface UserRepository {
    int count();

    int save(User user);

    int update(User user);

    int deleteById(int id);

    List<User> findAll();

    User findUserByUsername(String username);

    User findUserById(int id);
}
