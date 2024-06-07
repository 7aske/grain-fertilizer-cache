package com._7aske.grain.cache.interceptor;

import com._7aske.grain.cache.Cache;
import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.annotation.CacheEvict;

import java.lang.reflect.Method;

public class CacheEvictingProxyInterceptor extends AbstractCacheAwareProxyInterceptor {
    public CacheEvictingProxyInterceptor(Cache cache, CacheKeyGenerator cacheKeyGenerator) {
        super(cache, cacheKeyGenerator);
    }

    @Override
    public Object intercept(Object self,
                            Method method,
                            Object[] args,
                            Method superMethod) throws Throwable {
        if (evaluateCondition(method, args)) {
            CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);

            if (cacheEvict.allEntries()) {
                cache.clear();
            } else {
                cache.evict(generateCacheKey(method, args));
            }
        }


        return superMethod.invoke(self, args);
    }
}
