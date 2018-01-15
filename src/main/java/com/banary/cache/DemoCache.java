package com.banary.cache;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Callable;

@Component
public class DemoCache extends AbstractValueAdaptingCache {

    private static Logger LOGGER = LoggerFactory.getLogger(DemoCache.class);

    private static final Map<Object, Object> DEFAULT_ACHE_MAP = Maps.newConcurrentMap();

    private static final String DEFAULT_CACHE_NAME = "demo_cache";

    private String name = DEFAULT_CACHE_NAME;

    private Map<Object, Object> nativeCache = DEFAULT_ACHE_MAP;

    public DemoCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    public DemoCache(boolean allowNullValues, String name, Map<Object, Object> nativeCache) {
        super(allowNullValues);
        this.name = name;
        this.nativeCache = nativeCache;
    }

    @Override
    protected Object lookup(Object key) {
        return  nativeCache.get(key);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return nativeCache;
    }

    @Nullable
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        ValueWrapper result = get(key);
        //缓存有值直接返回
        if (result != null) {
            return (T) result.get();
        }
        //缓存无值重新加载，并写入缓存
        try{
            T value = valueLoader.call();
            put(key, value);
            return value;
        }catch (Exception e){
            LOGGER.error("Callback method execute error!e:{}", e.toString());
            return null;
        }
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        nativeCache.put(key, value);
    }

    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        nativeCache.remove(key);
    }

    @Override
    public void clear() {
        nativeCache.clear();
    }
}
