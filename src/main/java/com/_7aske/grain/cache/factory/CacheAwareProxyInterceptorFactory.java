package com._7aske.grain.cache.factory;

import com._7aske.grain.cache.helper.CacheNameResolver;
import com._7aske.grain.cache.Cache;
import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.core.reflect.AnnotationProxyInterceptorAbstractFactory;

import java.lang.reflect.Method;

public abstract class CacheAwareProxyInterceptorFactory implements AnnotationProxyInterceptorAbstractFactory {
    protected final CacheManager cacheManager;
    protected final CacheKeyGenerator cacheKeyGenerator;

    protected CacheAwareProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        this.cacheManager = cacheManager;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    protected Cache resolveCache(Method method) {
        String cacheName = CacheNameResolver.resolveCacheName(method);
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cache = cacheManager.createCache(cacheName);
        }

        return cache;
    }
}
