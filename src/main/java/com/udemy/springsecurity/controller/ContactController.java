package com.udemy.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/contact")
public class ContactController {
    @GetMapping
    String getContact() {
        return "Welcome to Contact Controller!";
    }
}
