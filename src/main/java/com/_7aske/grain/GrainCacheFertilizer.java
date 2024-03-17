package com._7aske.grain;

import com._7aske.grain.cache.CacheFactory;
import com._7aske.grain.cache.CacheManagerImpl;
import com._7aske.grain.cache.ConcurrentHashMapCacheFactory;
import com._7aske.grain.cache.SimpleCacheKeyGenerator;
import com._7aske.grain.core.cache.CacheKeyGenerator;
import com._7aske.grain.core.cache.CacheManager;
import com._7aske.grain.core.component.Grain;
import com._7aske.grain.core.configuration.GrainFertilizer;

@GrainFertilizer
public class GrainCacheFertilizer {
    @Grain
    public CacheManager cacheManagerImpl() {
        return new CacheManagerImpl(concurrentHashMapCacheFactory());
    }

    @Grain
    public CacheKeyGenerator cacheKeyGenerator() {
        return new SimpleCacheKeyGenerator();
    }

    @Grain
    public CacheFactory concurrentHashMapCacheFactory() {
        return new ConcurrentHashMapCacheFactory();
    }
}