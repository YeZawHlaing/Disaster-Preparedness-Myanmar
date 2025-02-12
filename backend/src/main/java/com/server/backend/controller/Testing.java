package com.server.backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backend/")
public class Testing {

    @GetMapping("/hi")
    public static String hello(){
        return "hello this is for project show";
    }
}
