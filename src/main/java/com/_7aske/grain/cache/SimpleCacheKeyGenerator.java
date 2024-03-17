package com._7aske.grain.cache;

import com._7aske.grain.core.cache.CacheKey;
import com._7aske.grain.core.cache.CacheKeyGenerator;

public class SimpleCacheKeyGenerator implements CacheKeyGenerator {
    @Override
    public CacheKey generate(Object... args) {
        return new SimpleCacheKey(args);
    }
}
