package com.phoenixhell.spring.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//自定义shiro redis缓存管理器

public class RedisCacheManager implements CacheManager {
    /**
     * @param cacheName  设置的认证或者授的名字
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache(cacheName);
    }
}
