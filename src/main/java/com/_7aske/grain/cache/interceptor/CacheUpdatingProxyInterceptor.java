package com._7aske.grain.cache.interceptor;

import com._7aske.grain.cache.Cache;
import com._7aske.grain.cache.CacheKeyGenerator;

import java.lang.reflect.Method;

public class CacheUpdatingProxyInterceptor extends AbstractCacheAwareProxyInterceptor {
    public CacheUpdatingProxyInterceptor(Cache cache, CacheKeyGenerator cacheKeyGenerator) {
        super(cache, cacheKeyGenerator);
    }

    @Override
    public Object intercept(Object self,
                            Method method,
                            Object[] args,
                            Method superMethod) throws Throwable {
        return cache.put(
                generateCacheKey(method, args),
                superMethod.invoke(self, args)
        );
    }
}
