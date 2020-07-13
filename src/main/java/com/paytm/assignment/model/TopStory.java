package com.paytm.assignment.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class TopStory implements Comparable<TopStory>,Serializable {

    private String title;
    private String url;
    private Integer score;
    private String time;
    private String by;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public int compareTo(TopStory topStory) {
          return  topStory.getScore() - this.getScore();
    }
}
