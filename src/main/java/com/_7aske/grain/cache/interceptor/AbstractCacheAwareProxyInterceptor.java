package com._7aske.grain.cache.interceptor;

import com._7aske.grain.cache.*;
import com._7aske.grain.cache.helper.CacheConditionEvaluator;
import com._7aske.grain.cache.helper.CacheKeyResolver;
import com._7aske.grain.core.reflect.ProxyInterceptor;

import java.lang.reflect.Method;

public abstract class AbstractCacheAwareProxyInterceptor implements ProxyInterceptor {
    protected final Cache cache;
    protected final CacheKeyGenerator cacheKeyGenerator;

    protected AbstractCacheAwareProxyInterceptor(Cache cache, CacheKeyGenerator cacheKeyGenerator) {
        this.cache = cache;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    protected CacheKey generateCacheKey(Method method, Object... args) {
        return CacheKeyResolver.resolveCacheKey(cacheKeyGenerator, method, args);
    }

    protected boolean evaluateCondition(Method method, Object... args) {
        return CacheConditionEvaluator.evaluateCondition(method, args);
    }
}
