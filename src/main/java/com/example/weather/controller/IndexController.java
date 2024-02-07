package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@Controller
public class IndexController {
    @Autowired
    WeatherService weatherService;
    @GetMapping("/")
    public String getPages(Model model) throws IOException {
        model.addAttribute("weather", weatherService.getWeather());
        return "index";
    }
}
