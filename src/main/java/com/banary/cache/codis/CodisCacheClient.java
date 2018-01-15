package com.banary.cache.codis;

import com.banary.cache.CacheClient;
import com.google.common.base.Strings;
import io.codis.jodis.RoundRobinJedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import redis.clients.jedis.Jedis;

import java.util.Objects;

public class CodisCacheClient implements CacheClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodisCacheClient.class);

    private RoundRobinJedisPool roundRobinJedisPool;

    private CodisCacheConfig codisCacheConfig;

    public CodisCacheClient() {
    }

    public CodisCacheClient(CodisCacheConfig codisCacheConfig) {
        this.codisCacheConfig = codisCacheConfig;
    }

    public void init() {
        Objects.requireNonNull(codisCacheConfig);
        initRoundRobinJedisPool();
    }

    protected void initRoundRobinJedisPool() {
        if (this.roundRobinJedisPool == null) {
            synchronized (CodisCacheClient.class) {
                if (roundRobinJedisPool == null) {
                    roundRobinJedisPool = RoundRobinJedisPool.create()
                            .poolConfig(codisCacheConfig.buildJedisPoolConfig())
                            .curatorClient(codisCacheConfig.getZkAddressAndPort(), codisCacheConfig.getZkSessionTimeOutMs())
                            .zkProxyDir(codisCacheConfig.getZkProxyDir())
                            .database(codisCacheConfig.getDatabase())
                            .password(Strings.isNullOrEmpty(codisCacheConfig.getPassword()) ? null : codisCacheConfig.getPassword())
                            .connectionTimeoutMs(codisCacheConfig.getConnectionTimeoutMs())
                            .soTimeoutMs(codisCacheConfig.getSoTimeoutMs())
                            .build();
                    LOGGER.info("codis client config,zkAddressAndPort:{},zkSessionTimeOutMs:{},zkProxyDir:{},connectTimeout:{},readTimeout:{},password:{},database:{}",
                            codisCacheConfig.getZkAddressAndPort(),
                            codisCacheConfig.getZkSessionTimeOutMs(),
                            codisCacheConfig.getZkProxyDir(),
                            codisCacheConfig.getConnectionTimeoutMs(),
                            codisCacheConfig.getSoTimeoutMs(),
                            codisCacheConfig.getPassword(),
                            codisCacheConfig.getDatabase());
                }
            }
        }
    }

    /**
     * 测试key是否存在
     *
     * @param key
     * @return true:存在,false:不存在
     */
    public boolean exists(String key) {
        Jedis jedis = roundRobinJedisPool.getResource();
        return jedis.exists(key);
    }

    public boolean set(String key, String value){
        Jedis jedis = roundRobinJedisPool.getResource();
        return jedis.set(key, value).equals("OK") ? true : false;
    }

    public void set(String key, String value, int seconds) {
        Jedis jedis = roundRobinJedisPool.getResource();
        jedis.setex(key, seconds, value);
    }

    /**
     * 用于删除已存在的键。不存在的 key 会被忽略
     *
     * @param keys
     * @return 被删除的键的数目
     */
    public Long del(String... keys) {
        Jedis jedis = roundRobinJedisPool.getResource();
        return jedis.del(keys);
    }

    /**
     * 查询
     *
     * @param key
     * @return
     */
    @Cacheable
    public String get(String key) {
        Jedis jedis = roundRobinJedisPool.getResource();
        return jedis.get(key);
    }

}
