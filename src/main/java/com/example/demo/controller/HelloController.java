package com.example.demo.controller;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String hello() {
        return "Welcome to Spring";
    }

    @GetMapping("/user")
    public User getOneUser() {
        return new User(1, "doanpt", "doandeptrai", "admin");
    }

    @GetMapping("/user/all")
    public List<User> getAllUser() {
        return userService.findAllUser();
    }

    @PostMapping("/add")
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
