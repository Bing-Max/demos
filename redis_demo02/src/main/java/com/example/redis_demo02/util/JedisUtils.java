package com.example.redis_demo02.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(20);

        jedisPool = new JedisPool(jedisPoolConfig, "192.168.246.137",6379,10,"tju158578");
    }

    public static Jedis getJedis() throws Exception {

        if(null != jedisPool){
            return jedisPool.getResource();
        }
        throw new Exception("JedisPool is null");
    }

}
