package com.anwjrrp33.blogsearchapi.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ConcurrentMapCache blogCache = new ConcurrentMapCache("blogs", getBlogsCacheMap(), false);
        ConcurrentMapCache keywordCache = new ConcurrentMapCache("keywords", getKeywordCacheMap(), false);
        cacheManager.setCaches(Arrays.asList(blogCache, keywordCache));

        return cacheManager;
    }

    private ConcurrentMap<Object, Object> getKeywordCacheMap() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(1)
                .build()
                .asMap();
    }

    private ConcurrentMap<Object, Object> getBlogsCacheMap() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .maximumSize(300)
                .build()
                .asMap();
    }
}
