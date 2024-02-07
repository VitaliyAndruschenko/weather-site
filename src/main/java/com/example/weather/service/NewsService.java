package com.example.weather.service;

import com.example.weather.dao.NewsDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NewsService {
    public JSONObject getNewsObject() throws IOException {
        URL newsAPIUrl = UriComponentsBuilder.fromUriString("https://newsapi.org/v2/top-headlines")
                .queryParam("country", "ru")
                .queryParam("apiKey", "11a99a1d005a44cf91132b96b3b3f7c0")
                .build()
                .toUri()
                .toURL();
        HttpURLConnection newsAPICon = (HttpURLConnection) newsAPIUrl.openConnection();
        newsAPICon.setRequestMethod("GET");
        newsAPICon.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(newsAPICon.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    public Page<NewsDAO> getNews(Pageable pageable) throws IOException{
        List<NewsDAO> newsDAOList = new ArrayList<>();
        for (int i = 0; i < getNewsObject().getJSONArray("articles").length(); i++) {
            NewsDAO newsDAO = new NewsDAO();
            newsDAO.setUrl(getNewsObject().getJSONArray("articles").getJSONObject(i).getString("url"));
            newsDAO.setTitle(getNewsObject().getJSONArray("articles").getJSONObject(i).getString("title"));
            newsDAOList.add(newsDAO);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), newsDAOList.size());
        return new PageImpl<>(newsDAOList.subList(start, end), pageable, newsDAOList.size());
    }
}
