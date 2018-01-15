package com.banary.cache.codis;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

public class CodisCacheManager implements Cache {

    private static final String CACHE_NAME= "codis_cache";

    private CodisCacheClient codisCacheClient;

    @Override
    public String getName() {
        return CACHE_NAME;
    }

    @Override
    public Object getNativeCache() {
        return codisCacheClient;
    }

    @Nullable
    @Override
    public ValueWrapper get(Object key) {
        String value = codisCacheClient.get((String)key);
        return (value != null ? new SimpleValueWrapper(value) : null);
    }

    @Nullable
    @Override
    public <T> T get(Object key, @Nullable Class<T> className) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object key, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        this.codisCacheClient.set((String)key, value.toString());
    }

    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object o, @Nullable Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
