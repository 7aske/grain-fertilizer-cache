package com._7aske.grain.cache.factory;

import com._7aske.grain.cache.helper.CacheNameResolver;
import com._7aske.grain.cache.Cache;
import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.core.reflect.ProxyInterceptorAbstractFactory;

import java.lang.reflect.Method;

public abstract class CacheAwareProxyInterceptorFactory implements ProxyInterceptorAbstractFactory {
    protected final CacheManager cacheManager;
    protected final CacheKeyGenerator cacheKeyGenerator;

    protected CacheAwareProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        this.cacheManager = cacheManager;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    @Override
    public boolean supports(Object object) {
        if (object instanceof Method method) {
            return method.isAnnotationPresent(getDiscriminatorType());
        }

        if (object instanceof Class) {
            for (Method method : ((Class<?>) object).getMethods()) {
                if (method.isAnnotationPresent(getDiscriminatorType())) {
                    return true;
                }
            }
        }

        return false;
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
