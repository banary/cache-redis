package com.banary.cache.codis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.Objects;

public class CodisCacheConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(CodisCacheConfig.class);

    public CodisCacheConfig(){
    }

    /**
     * 默认的codis连接参数
     */
    private static final Integer DEFAULT_MAX_TOTAL			=	500;
    private static final Integer DEFAULT_MAX_IDLE			=	100;
    private static final Integer DEFAULT_MIN_IDLE			=	0;
    private static final Integer DEFAULT_MAXWAIT_MILLIS		=	20000;
    private static final String  DEFAULT_ZKPROXYDIR			=	"/zk/codis/db_nonobank/proxy";
    private static final Integer DEFAULT_ZKSESSIONTIMEOUTMS	=	30000;
    private static final String  DEFAULT_ENVIRONMENT		=	"defualt";
    private static final boolean DEFAULT_DEBUG				=	false;
    private static final Integer DEFAULT_TIMEOUT			= Protocol.DEFAULT_TIMEOUT;
    private static final Integer DEFAULT_DATABASE			= Protocol.DEFAULT_DATABASE;

    /**
     * JedisPoolConfig
     */
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle	= DEFAULT_MAX_IDLE;
    private int minIdle	= DEFAULT_MIN_IDLE;
    private int maxWaitMillis =	DEFAULT_MAXWAIT_MILLIS;
    /**
     * zookeeper相关配置
     */
    private String zkAddressAndPort;
    private int zkSessionTimeOutMs = DEFAULT_ZKSESSIONTIMEOUTMS;
    private String zkProxyDir = DEFAULT_ZKPROXYDIR;
    /**
     * timeout设置
     */

    private int database = DEFAULT_DATABASE;
    private String password;
    private int soTimeoutMs	= DEFAULT_TIMEOUT;
    private int connectionTimeoutMs = DEFAULT_TIMEOUT;
    private String envrionment = DEFAULT_ENVIRONMENT;
    private boolean debug = DEFAULT_DEBUG;

    protected JedisPoolConfig buildJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Objects.requireNonNull(maxIdle,"maxIdle is null"));
        jedisPoolConfig.setMaxTotal(Objects.requireNonNull(maxTotal,"maxTotal is null"));
        jedisPoolConfig.setMinIdle(Objects.requireNonNull(minIdle,"minIdle is null"));
        jedisPoolConfig.setMaxWaitMillis(Objects.requireNonNull(maxWaitMillis,"maxWaitMillis is null"));
        return jedisPoolConfig;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getZkSessionTimeOutMs() {
        return zkSessionTimeOutMs;
    }

    public void setZkSessionTimeOutMs(int zkSessionTimeOutMs) {
        this.zkSessionTimeOutMs = zkSessionTimeOutMs;
    }

    public String getZkAddressAndPort() {
        return zkAddressAndPort;
    }

    public void setZkAddressAndPort(String zkAddressAndPort) {
        this.zkAddressAndPort = zkAddressAndPort;
    }

    public String getZkProxyDir() {
        return zkProxyDir;
    }

    public void setZkProxyDir(String zkProxyDir) {
        this.zkProxyDir = zkProxyDir;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSoTimeoutMs() {
        return soTimeoutMs;
    }

    public void setSoTimeoutMs(int soTimeoutMs) {
        this.soTimeoutMs = soTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getEnvrionment() {
        return envrionment;
    }

    public void setEnvrionment(String envrionment) {
        this.envrionment = envrionment;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
