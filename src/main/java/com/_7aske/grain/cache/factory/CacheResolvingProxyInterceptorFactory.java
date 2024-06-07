package com._7aske.grain.cache.factory;

import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.cache.annotation.Cacheable;
import com._7aske.grain.cache.interceptor.CacheResolvingProxyInterceptor;
import com._7aske.grain.core.reflect.ProxyInterceptor;
import com._7aske.grain.logging.Logger;
import com._7aske.grain.logging.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CacheResolvingProxyInterceptorFactory extends CacheAwareProxyInterceptorFactory {
    private static final Logger logger = LoggerFactory.getLogger(CacheResolvingProxyInterceptorFactory.class);

    public CacheResolvingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        super(cacheManager, cacheKeyGenerator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<T> getDiscriminatorType() {
        return (Class<T>) Cacheable.class;
    }

    @Override
    public ProxyInterceptor create(Method method) {
        logger.debug("Creating CacheResolvingProxyInterceptor for method: " + method.getName());
        return new CacheResolvingProxyInterceptor(resolveCache(method), cacheKeyGenerator);
    }
}
