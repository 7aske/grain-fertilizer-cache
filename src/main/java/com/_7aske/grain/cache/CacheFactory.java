package com._7aske.grain.cache;

// @Todo make it generic
public interface CacheFactory {
    Cache createCache(String name);
}
