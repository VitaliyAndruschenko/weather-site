package com.example.weather.dao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherDAO {
    //Температура
    private Integer temp;
    //Иконка температуры
    private String icon;
    //Скорость ветра
    private Integer windSpeed;
    //Влажность воздуха
    private Integer humidity;
    //Направление ветра
    private String windDir;
    //Дата
    private String date;
    public int getDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.getDayOfMonth();
    }
    public String getMonthOfDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        String month = String.valueOf(localDate.getMonth()).toLowerCase();
        return month.substring(0,1).toUpperCase() + month.substring(1).substring(0, 2);
    }
    public String getDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date);
        String month = String.valueOf(localDate.getDayOfWeek()).toLowerCase();
        return month.substring(0,1).toUpperCase() + month.substring(1);
    }
    public String getIcon(String icon) {
        return "https://yastatic.net/weather/i/icons/funky/dark/" + icon + ".svg";
    }
}
