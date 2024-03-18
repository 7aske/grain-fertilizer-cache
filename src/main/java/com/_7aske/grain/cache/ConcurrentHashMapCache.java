package com._7aske.grain.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapCache implements Cache {
    private final String name;
    private final Map<CacheKey, Object> cache;

    public ConcurrentHashMapCache(String name) {
        this.name = name;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object get(CacheKey key) {
        return cache.get(key);
    }

    @Override
    public Object put(CacheKey key, Object value) {
        cache.put(key, value);
        return value;
    }

    @Override
    public void evict(CacheKey key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean contains(CacheKey key) {
        return cache.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }
}
