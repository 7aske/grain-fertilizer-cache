package com._7aske.grain.cache;

public class ConcurrentHashMapCacheFactory implements CacheFactory {
    @Override
    public ConcurrentHashMapCache createCache(String name) {
        return new ConcurrentHashMapCache(name);
    }
}
