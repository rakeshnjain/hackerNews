# hackerNews

## Overview

We are using hazelcast to cache java object. We are calling HN https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty end point which give top 500 stories ids.
We are using Rxjava and parallel stream to get top 10 scores story out of 500 stories and puting top 10 story in hazelcast map with name 'topStories' for 10 minutes TTL.
There is no HN api call for next 10 minutes so we have only one HN call for top stories in 10 minutes.

We are using H2 in memory database and Spring data to save top stories. 


HazelCast Mancenter URL :  http://localhost:8080/hazelcast-mancenter

Top stories URL : http://localhost:8081/api/v1/hackernews/top-stories

```javascript
[
    {
        "title": "Don't close your MacBook with a cover over the camera",
        "url": "https://support.apple.com/en-us/HT211148",
        "score": 772,
        "time": "1594408423",
        "by": "ra7"
    },
    {
        "title": "How to Understand Things",
        "url": "https://nabeelqu.co/understanding",
        "score": 759,
        "time": "1594479528",
        "by": "ingve"
    },
    {
        "title": "Do not remain nameless to yourself (1966)",
        "url": "https://lettersofnote.com/2015/10/23/do-not-remain-nameless-to-yourself/",
        "score": 731,
        "time": "1594522691",
        "by": "andrewnc"
    },
    {
        "title": "App suddenly crashing on startup due to FBSDKRestrictiveDataFilterManager.m",
        "url": "https://github.com/facebook/facebook-ios-sdk/issues/1427",
        "score": 590,
        "time": "1594378805",
        "by": "reubensutton"
    },
    {
        "title": "Just Too Efficient",
        "url": "https://www.tbray.org/ongoing/When/202x/2020/07/05/Too-Efficient",
        "score": 544,
        "time": "1594534692",
        "by": "MindGods"
    },
    {
        "title": "Amazon says email banning TikTok from employee phones was ‘sent in error’",
        "url": "https://twitter.com/scotthickle/status/1281631749533990914",
        "score": 541,
        "time": "1594400825",
        "by": "danso"
    },
    {
        "title": "Ask HN: What's the worst piece of software you use everyday?",
        "url": null,
        "score": 496,
        "time": "1594486427",
        "by": "guu"
    },
    {
        "title": "PG: The biggest source of stress for me at YC was running HN",
        "url": "https://twitter.com/paulg/status/1282052801347100675",
        "score": 459,
        "time": "1594504436",
        "by": "ilamont"
    },
    {
        "title": "Linux kernel in-tree Rust support",
        "url": "https://lore.kernel.org/lkml/CAKwvOdmuYc8rW_H4aQG4DsJzho=F+djd68fp7mzmBp3-wY--Uw@mail.gmail.com/T/#u",
        "score": 455,
        "time": "1594451246",
        "by": "littlestymaar"
    },
    {
        "title": "LibreOffice: The Next Five Years",
        "url": "https://lwn.net/SubscriberLink/825598/21fb7c2a3f9358e7/",
        "score": 408,
        "time": "1594373282",
        "by": "ingve"
    }
]
```



Past stories URL : http://localhost:8081/api/v1/hackernews/past-stories



```javascript
[
    {
        "id": 1,
        "title": "Don't close your MacBook with a cover over the camera",
        "url": "https://support.apple.com/en-us/HT211148",
        "score": 780,
        "time": "1594408423",
        "by": "ra7"
    },
    {
        "id": 2,
        "title": "How to Understand Things",
        "url": "https://nabeelqu.co/understanding",
        "score": 779,
        "time": "1594479528",
        "by": "ingve"
    },
    {
        "id": 3,
        "title": "Do not remain nameless to yourself (1966)",
        "url": "https://lettersofnote.com/2015/10/23/do-not-remain-nameless-to-yourself/",
        "score": 755,
        "time": "1594522691",
        "by": "andrewnc"
    },
    {
        "id": 4,
        "title": "I Know What You Download on BitTorrent",
        "url": "http://iknowwhatyoudownload.com",
        "score": 644,
        "time": "1594653455",
        "by": "easterncalculus"
    },
    {
        "id": 5,
        "title": "Just Too Efficient",
        "url": "https://www.tbray.org/ongoing/When/202x/2020/07/05/Too-Efficient",
        "score": 556,
        "time": "1594534692",
        "by": "MindGods"
    },
    {
        "id": 6,
        "title": "Amazon says email banning TikTok from employee phones was ‘sent in error’",
        "url": "https://twitter.com/scotthickle/status/1281631749533990914",
        "score": 543,
        "time": "1594400825",
        "by": "danso"
    },
    {
        "id": 7,
        "title": "Ask HN: What's the worst piece of software you use everyday?",
        "url": null,
        "score": 515,
        "time": "1594486427",
        "by": "guu"
    },
    {
        "id": 8,
        "title": "Show HN: Primo – all-in-one IDE, CMS, component library, static site generator",
        "url": "https://primo.af",
        "score": 502,
        "time": "1594644710",
        "by": "mmmateo"
    },
    {
        "id": 9,
        "title": "PG: The biggest source of stress for me at YC was running HN",
        "url": "https://twitter.com/paulg/status/1282052801347100675",
        "score": 471,
        "time": "1594504436",
        "by": "ilamont"
    },
    {
        "id": 10,
        "title": "Linux kernel in-tree Rust support",
        "url": "https://lore.kernel.org/lkml/CAKwvOdmuYc8rW_H4aQG4DsJzho=F+djd68fp7mzmBp3-wY--Uw@mail.gmail.com/T/#u",
        "score": 460,
        "time": "1594451246",
        "by": "littlestymaar"
    }
]
```
