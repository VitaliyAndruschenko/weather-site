package com.example.weather.service;

import com.example.weather.dao.WeatherDAO;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class WeatherService {
    public JSONObject getWeatherObject(String width, String longitude) throws IOException {
        URL yandexWeatherUrl = new URL("https://api.weather.yandex.ru/v2/informers?lat=55.7522&lon=37.6156");
        HttpURLConnection yandexWeatherCon = (HttpURLConnection) yandexWeatherUrl.openConnection();
        yandexWeatherCon.setRequestMethod("GET");
        yandexWeatherCon.setRequestProperty("X-Yandex-API-Key", "fecb42ed-bfd3-4946-8310-80def00f9b11");
        yandexWeatherCon.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(yandexWeatherCon.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    public WeatherDAO getWeather() throws IOException{
        WeatherDAO weatherDAO = new WeatherDAO();
        weatherDAO.setTemp(getWeatherObject().getJSONObject("fact").getInt("temp"));
        weatherDAO.setIcon(getWeatherObject().getJSONObject("fact").getString("icon"));
        weatherDAO.setWindSpeed(getWeatherObject().getJSONObject("fact").getInt("wind_speed"));
        weatherDAO.setHumidity(getWeatherObject().getJSONObject("fact").getInt("humidity"));
        weatherDAO.setWindDir(getWeatherObject().getJSONObject("fact").getString("wind_dir"));
        weatherDAO.setDate(getWeatherObject().getJSONObject("forecast").getString("date"));
        return weatherDAO;
    }
}
