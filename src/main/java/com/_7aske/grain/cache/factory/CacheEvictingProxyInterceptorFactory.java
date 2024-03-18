package com._7aske.grain.cache.factory;

import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.cache.interceptor.CacheEvictingProxyInterceptor;
import com._7aske.grain.core.reflect.ProxyInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CacheEvictingProxyInterceptorFactory extends CacheAwareProxyInterceptorFactory {

    public CacheEvictingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        super(cacheManager, cacheKeyGenerator);
    }

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return com._7aske.grain.core.cache.annotation.CacheEvict.class;
    }

    @Override
    public ProxyInterceptor create(Method method) {
        return new CacheEvictingProxyInterceptor(resolveCache(method), cacheKeyGenerator);
    }
}
