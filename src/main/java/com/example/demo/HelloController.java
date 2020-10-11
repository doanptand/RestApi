package com.example.demo;

import com.example.demo.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "Welcome to Spring";
    }

    @GetMapping("/user")
    public User getOneUser() {
        return new User(1,"doanpt","doandeptrai", "admin");
    }
}
