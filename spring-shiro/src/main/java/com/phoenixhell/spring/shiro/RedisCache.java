package com.phoenixhell.spring.shiro;

import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.support.UiApplicationContextUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * 如果不加 @Component 拿不到redistemplate
 */
//@Component
public class RedisCache<K, V> implements Cache<K, V> {
    @Resource
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key");
        V v = redisTemplate.opsForValue().get(k.toString());
        System.out.println(v);
        //if(v==null){
        //    return null;
        //}
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key");
        //key 设置为string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(k, v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
