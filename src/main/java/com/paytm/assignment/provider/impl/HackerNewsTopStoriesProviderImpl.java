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
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class HackerNewsTopStoriesProviderImpl {

    @Autowired
    private CacheProvider hazelCastCacheProvide;

    @Autowired
    HackerNewsTopStoryDescriptionProviderImpl hackerNewsTopStoryDescriptionProvider;

    @Value("${hazelcast.ttl:10}")
    private long hazelcastTtl;

    @Value("${hacker.news.topstories.url}")
    private String topStoriesAPiUrl;

    @Autowired
    RestTemplate restTemplate;

    public static final String TOP_STORIES_MAP = "topStories";
    public static final String TOP10_STORIES_KEY = "TOP10";

    public List<TopStory> getTopStories() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ArrayList<Integer> response =
                restTemplate.exchange(topStoriesAPiUrl, HttpMethod.GET,httpEntity, ArrayList.class).getBody();

        List<Observable<TopStory>> observableList = new ArrayList<>();
        response.forEach(topStory -> {
            Observable<TopStory>  topStoryObservable= filterTopStories(topStory);
            observableList.add(topStoryObservable);
        });

        List<TopStory> topStoryList = new ArrayList<>();
        observableList.parallelStream().forEach(topStoryObservable -> {
            topStoryList.add(topStoryObservable.toBlocking().single());
        });
        Collections.sort(topStoryList);

        hazelCastCacheProvide.put(TOP_STORIES_MAP,TOP10_STORIES_KEY,
                new ArrayList<TopStory>(topStoryList.subList(0, 10)),hazelcastTtl, TimeUnit.MINUTES);
        return topStoryList.subList(0,10);
    }


    private Observable<TopStory> filterTopStories(Integer story) {
         return Observable.create((Subscriber<? super TopStory> s) -> {
                    s.onNext(hackerNewsTopStoryDescriptionProvider.getTopStories(story));
                    s.onCompleted();
         }).doOnCompleted(() -> logTime("story fetch completed for story " + story))
                .subscribeOn(Schedulers.io());
    }

    private void logTime(String s) {
        System.out.println(s);
    }

}