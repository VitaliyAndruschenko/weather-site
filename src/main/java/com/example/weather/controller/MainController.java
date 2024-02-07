package com.example.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/https://api.weather.yandex.ru/v2/informers?lat=45.3531&lon=36.4743")
    public String getPage() {
        return "index";
    }
}
