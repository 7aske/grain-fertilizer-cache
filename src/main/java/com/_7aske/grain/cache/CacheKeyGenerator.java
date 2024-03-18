package com._7aske.grain.cache;

public interface CacheKeyGenerator {
    CacheKey generate(Object... args);
}
