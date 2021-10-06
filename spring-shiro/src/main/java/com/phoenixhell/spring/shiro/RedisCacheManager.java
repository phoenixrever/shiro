package com.phoenixhell.spring.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

//自定义shiro redis缓存管理器
//@Component
public class RedisCacheManager implements CacheManager {
    /**
     * @param s  设置的认证或者授的名字
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new RedisCache<K,V>();
    }
}
