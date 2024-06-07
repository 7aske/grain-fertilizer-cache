package com._7aske.grain.cache.factory;

import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.cache.annotation.CachePut;
import com._7aske.grain.cache.interceptor.CacheUpdatingProxyInterceptor;
import com._7aske.grain.core.reflect.ProxyInterceptor;
import com._7aske.grain.logging.Logger;
import com._7aske.grain.logging.LoggerFactory;

import java.lang.reflect.Method;

public class CacheUpdatingProxyInterceptorFactory extends CacheAwareProxyInterceptorFactory {
    private static final Logger logger = LoggerFactory.getLogger(CacheUpdatingProxyInterceptorFactory.class);

    public CacheUpdatingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        super(cacheManager, cacheKeyGenerator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<T> getDiscriminatorType() {
        return (Class<T>) CachePut.class;
    }

    @Override
    public ProxyInterceptor create(Method method) {
        logger.debug("Creating CacheUpdatingProxyInterceptor for method: " + method.getName());
        return new CacheUpdatingProxyInterceptor(resolveCache(method), cacheKeyGenerator);
    }
}
