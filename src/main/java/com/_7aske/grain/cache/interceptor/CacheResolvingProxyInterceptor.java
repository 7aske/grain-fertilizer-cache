package com._7aske.grain.cache.interceptor;

import com._7aske.grain.cache.Cache;
import com._7aske.grain.cache.CacheKey;
import com._7aske.grain.cache.CacheKeyGenerator;

import java.lang.reflect.Method;

public class CacheResolvingProxyInterceptor extends AbstractCacheAwareProxyInterceptor {
    public CacheResolvingProxyInterceptor(Cache cache, CacheKeyGenerator cacheKeyGenerator) {
        super(cache, cacheKeyGenerator);
    }

    @Override
    public Object intercept(Object self,
                            Method method,
                            Object[] args,
                            Method superMethod) throws Throwable {
        CacheKey key = generateCacheKey(method, args);
        if (cache.contains(key)) {
            return cache.get(key);
        }

        return cache.put(key, superMethod.invoke(self, args));
    }
}
