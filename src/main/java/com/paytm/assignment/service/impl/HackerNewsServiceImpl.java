package com.paytm.assignment.service.impl;

import com.paytm.assignment.model.TopStory;
import com.paytm.assignment.provider.CacheProvider;
import com.paytm.assignment.provider.impl.HackerNewsTopStoriesProviderImpl;
import com.paytm.assignment.repository.TopStoryRepository;
import com.paytm.assignment.service.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackerNewsServiceImpl implements HackerNewsService{

    public static final String TOP_STORIES_MAP = "topStories";
    public static final String TOP10_STORIES_KEY = "TOP10";

    @Autowired(required=false)
    TopStoryRepository topStoryRepository;

    @Autowired
    HackerNewsTopStoriesProviderImpl hackerNewsTopStoriesProvider;

    @Autowired
    private CacheProvider hazelCastCacheProvide;

    @Override
    public List<TopStory> getTopStories() {
         if (hazelCastCacheProvide.get(TOP10_STORIES_KEY, TOP_STORIES_MAP) != null) {
               return hazelCastCacheProvide.get(TOP10_STORIES_KEY, TOP_STORIES_MAP);
         } else {
             List<TopStory> topStoryList = hackerNewsTopStoriesProvider.getTopStories();
             try {
                 saveTopStory(topStoryList);
             } catch(Exception ex) {

             }
             return topStoryList;
         }
    }

    @Override
    public List<TopStory> getPastStories() {
        return (List<TopStory>)topStoryRepository.findAll();
    }

    @Override
    public void saveTopStory(List<TopStory> topStories) {
         topStoryRepository.save(topStories.get(0));
    }
}
