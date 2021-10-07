package com.phoenixhell.spring.shiro;

import com.phoenixhell.spring.util.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * 继承泛型
 * 全部保留
 * class C1<T2,T1,A> extends Father<T1,T2>
 * 部分保留
 * class C2<T2> extends Father<Integer,T2>
 * 不保留
 * class C3<A,B> extends Father<String,Integer>
 * 擦除类型
 * class C4<A,B> extends Fathe
 */
@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {
    private String cacheName;

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    private RedisTemplate<String, V> getRedisTemplate() {
        return ApplicationContextUtils.getBean("redisTemplate");
    }

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        System.out.println("get key:" + k);
        if (k == null) {
            return null;
        }
        BoundHashOperations<String, K, V> operations = redisTemplate.boundHashOps(cacheName);
        V v = operations.get(k);
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("puy key:" + k);
        System.out.println("puy value:" + v);
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        if (k == null) {
            log.warn("Saving a null key is meaningless, return value directly without call Redis.");
            return v;
        }
        BoundHashOperations<String, K, V> operations = redisTemplate.boundHashOps(cacheName);

//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        operations.put(k, v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        BoundHashOperations<String, K, V> operations = redisTemplate.boundHashOps(cacheName);
        operations.delete(k);
        System.out.println("remove");
        return null;
    }

    @Override
    public void clear() throws CacheException {
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        redisTemplate.delete(cacheName);
    }

    @Override
    public int size() {
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        BoundHashOperations<String, K, V> operations = redisTemplate.boundHashOps(cacheName);
        int size = Objects.requireNonNull(operations.size()).intValue();
        return size;
    }

    @Override
    public Set<K> keys() {
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        BoundHashOperations<String, K, V> operations = redisTemplate.boundHashOps(cacheName);
        return operations.keys();
    }

    @Override
    public Collection<V> values() {
        RedisTemplate<String, V> redisTemplate = getRedisTemplate();
        BoundHashOperations<String, K, V> operations = redisTemplate.boundHashOps(cacheName);
        return operations.values();
    }
}
