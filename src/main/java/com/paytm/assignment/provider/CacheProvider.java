/*
 * Copyright (c) 2017 JCPenney Co. All rights reserved.
 */

package com.paytm.assignment.provider;

import com.paytm.assignment.model.TopStory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheProvider {
    void put(String mapName, String key, List<TopStory> object, long timeToLive, TimeUnit timeUnit);

    List<TopStory> get(String key, String mapName);

    void clearAllMapEntries(String mapName);

    void removeByKey(String key, String mapName);

}
