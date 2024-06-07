package com._7aske.grain.cache.factory;

import com._7aske.grain.cache.CacheKeyGenerator;
import com._7aske.grain.cache.CacheManager;
import com._7aske.grain.cache.annotation.CacheEvict;
import com._7aske.grain.cache.interceptor.CacheEvictingProxyInterceptor;
import com._7aske.grain.core.reflect.ProxyInterceptor;
import com._7aske.grain.logging.Logger;
import com._7aske.grain.logging.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CacheEvictingProxyInterceptorFactory extends CacheAwareProxyInterceptorFactory {
    private static final Logger logger = LoggerFactory.getLogger(CacheEvictingProxyInterceptorFactory.class);

    public CacheEvictingProxyInterceptorFactory(CacheManager cacheManager, CacheKeyGenerator cacheKeyGenerator) {
        super(cacheManager, cacheKeyGenerator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<T> getDiscriminatorType() {
        return (Class<T>) CacheEvict.class;
    }

    @Override
    public ProxyInterceptor create(Method method) {
        logger.debug("Creating CacheEvictingProxyInterceptor for method: " + method.getName());
        return new CacheEvictingProxyInterceptor(resolveCache(method), cacheKeyGenerator);
    }
}
