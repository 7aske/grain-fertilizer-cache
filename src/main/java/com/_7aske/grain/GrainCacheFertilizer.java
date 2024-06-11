package com._7aske.grain;

import com._7aske.grain.cache.*;
import com._7aske.grain.cache.factory.CacheEvictingProxyInterceptorFactory;
import com._7aske.grain.cache.factory.CacheResolvingProxyInterceptorFactory;
import com._7aske.grain.cache.factory.CacheUpdatingProxyInterceptorFactory;
import com._7aske.grain.core.component.Grain;
import com._7aske.grain.core.component.Order;
import com._7aske.grain.core.configuration.GrainFertilizer;
import com._7aske.grain.core.reflect.ProxyInterceptorAbstractFactory;

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
    public ProxyInterceptorAbstractFactory cacheResolvingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        return new CacheResolvingProxyInterceptorFactory(cacheManager, cacheKeyGenerator);
    }

    @Grain
    public ProxyInterceptorAbstractFactory cacheUpdatingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        return new CacheUpdatingProxyInterceptorFactory(cacheManager, cacheKeyGenerator);
    }

    @Grain
    public ProxyInterceptorAbstractFactory cacheEvictingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        return new CacheEvictingProxyInterceptorFactory(cacheManager, cacheKeyGenerator);
    }
}