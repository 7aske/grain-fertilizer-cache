package com._7aske.grain.cache;

import com._7aske.grain.core.cache.Cache;
import com._7aske.grain.core.cache.CacheManager;

import java.util.HashMap;
import java.util.Map;

public class CacheManagerImpl implements CacheManager {
    private final Map<String, Cache> caches = new HashMap<>();
    private final CacheFactory cacheFactory;

    public CacheManagerImpl(CacheFactory cacheFactory) {
        this.cacheFactory = cacheFactory;
    }

    @Override
    public Cache createCache(String name) {
        if (caches.containsKey(name)) {
            throw new IllegalArgumentException("Cache with name " + name + " already exists");
        }
        Cache cache = cacheFactory.createCache(name);
        caches.put(name, cache);
        return cache;
    }

    @Override
    public void addCache(Cache cache) {
        caches.put(cache.getName(), cache);
    }

    @Override
    public Cache getCache(String name) {
        return caches.get(name);
    }

    @Override
    public void removeCache(String name) {
        caches.remove(name);
    }

    @Override
    public void clearCache(String name) {
        caches.get(name).clear();
    }

    @Override
    public void clearAllCaches() {
        caches.values().forEach(Cache::clear);
    }
}
