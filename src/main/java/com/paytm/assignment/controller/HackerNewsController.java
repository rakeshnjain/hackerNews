package com.paytm.assignment.controller;

import com.paytm.assignment.model.TopStory;
import com.paytm.assignment.service.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${microservice.contextPath}")
public class HackerNewsController {

    @Autowired
    HackerNewsService hackerNewsService;

    @GetMapping("/top-stories")
    public List<TopStory> getTopStories() {
        return hackerNewsService.getTopStories();
    }


    @GetMapping("/past-stories")
    public List<TopStory> getPastStories() {
        return hackerNewsService.getPastStories();
    }
}
