package com._7aske.grain.cache;

import com._7aske.grain.core.cache.Cache;

// @Todo make it generic
public interface CacheFactory {
    Cache createCache(String name);
}
