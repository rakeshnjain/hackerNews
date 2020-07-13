package com.paytm.assignment.provider.impl;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.paytm.assignment.model.TopStory;
import com.paytm.assignment.provider.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class HazelcastProviderImpl implements CacheProvider {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Override
    public void put(String mapName, String key, List<TopStory> object, long timeToLive, TimeUnit timeUnit) {
        IMap<String, Object> objectIMapCache =
                hazelcastInstance.getMap(mapName);
        objectIMapCache.put(key, object, timeToLive, timeUnit);
    }

    @Override
    public List<TopStory> get(String key, String mapName) {
        IMap<String, Object> objectIMapCache =
                hazelcastInstance.getMap(mapName);
        return (List<TopStory> ) objectIMapCache.get(key);
    }


    @Override
    public void clearAllMapEntries(String mapName) {
        IMap<String, Object> objectIMapCache =
                hazelcastInstance.getMap(mapName);
        objectIMapCache.clear();
    }

    @Override
    public void removeByKey(String key, String mapName) {
            IMap<String, Object> objectIMapCache =
                    hazelcastInstance.getMap(mapName);
            objectIMapCache.remove(key);
    }

}