package com._7aske.grain;

import com._7aske.grain.cache.CacheFactory;
import com._7aske.grain.cache.CacheManagerImpl;
import com._7aske.grain.cache.ConcurrentHashMapCacheFactory;
import com._7aske.grain.cache.SimpleCacheKeyGenerator;
import com._7aske.grain.cache.factory.CacheEvictingProxyInterceptorFactory;
import com._7aske.grain.cache.factory.CacheResolvingProxyInterceptorFactory;
import com._7aske.grain.cache.factory.CacheUpdatingProxyInterceptorFactory;
import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.core.component.Grain;
import com._7aske.grain.core.configuration.GrainFertilizer;
import com._7aske.grain.core.reflect.AnnotationProxyInterceptorAbstractFactory;

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

    @Grain
    public AnnotationProxyInterceptorAbstractFactory cacheResolvingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        return new CacheResolvingProxyInterceptorFactory(cacheManager, cacheKeyGenerator);
    }

    @Grain
    public AnnotationProxyInterceptorAbstractFactory cacheUpdatingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        return new CacheUpdatingProxyInterceptorFactory(cacheManager, cacheKeyGenerator);
    }

    @Grain
    public AnnotationProxyInterceptorAbstractFactory cacheEvictingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        return new CacheEvictingProxyInterceptorFactory(cacheManager, cacheKeyGenerator);
    }
}