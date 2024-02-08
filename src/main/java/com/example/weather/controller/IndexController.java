package com.example.weather.controller;

import com.example.weather.service.JDBCConnector;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class IndexController {
    @Autowired
    WeatherService weatherService;
    @Autowired
    JDBCConnector jdbcConnector;
    @GetMapping("/")
    public String getPages(Model model, String name) throws IOException, SQLException {
        jdbcConnector.getCoordinatesCity(name);
        model.addAttribute("weather", weatherService.getWeather());
        return "index";
    }
}
