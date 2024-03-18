package com._7aske.grain.cache;

public class SimpleCacheKeyGenerator implements CacheKeyGenerator {
    @Override
    public CacheKey generate(Object... args) {
        return new SimpleCacheKey(args);
    }
}
