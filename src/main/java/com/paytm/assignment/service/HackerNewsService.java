package com.paytm.assignment.service;

import com.paytm.assignment.model.TopStory;

import java.util.List;

public interface HackerNewsService {

    List<TopStory> getTopStories();

    List<TopStory> getPastStories();

    void saveTopStory(List<TopStory> topStories);

}
