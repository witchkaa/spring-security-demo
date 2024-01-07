package com.example.springsecuritydemo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("/")
    public String getHomePage() {
        return "Welcome!";
    }

    @GetMapping("/api/greetings")
    public ResponseEntity<Map<String, String>> getGreetings() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "Hello, %s!".formatted(userDetails.getUsername())));
    }
}
