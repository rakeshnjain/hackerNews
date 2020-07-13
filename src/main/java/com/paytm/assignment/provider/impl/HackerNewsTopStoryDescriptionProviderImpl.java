package com.paytm.assignment.provider.impl;

import com.paytm.assignment.model.TopStory;
import com.paytm.assignment.provider.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

@Component
public class HackerNewsTopStoryDescriptionProviderImpl {

    @Value("${hacker.news.topstory.description.url}")
    private String topStoryDescriptionUrl;

    @Autowired
    RestTemplate restTemplate;

    public TopStory getTopStories(Integer storyId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        Map<String, String> params = new HashMap<>();
        params.put("storyId", storyId.toString());
        TopStory response =
                restTemplate.exchange(topStoryDescriptionUrl, HttpMethod.GET,httpEntity, TopStory.class,params).getBody();
        return response;
    }
}