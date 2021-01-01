package com.example.redis_demo01.controller;

import com.example.redis_demo01.util.JedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    public static final String REDIS_LOCK = "LOCK_ING";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson;

    @Value("${server.port}")
    private String port;

    @GetMapping("/buy_goods")
    public String buyGoods() throws Exception {

        String val = UUID.randomUUID().toString() + Thread.currentThread().getName();
        RLock lock = redisson.getLock(val);

        lock.lock();
        try{

            String result = redisTemplate.opsForValue().get("good:001");
            int count = null == result ? 0 : Integer.parseInt(result);

            if(count > 0){
                int rest = count - 1;
                redisTemplate.opsForValue().set("good:001", String.valueOf(rest));
                System.out.println("成功买到商品，库存：" + rest + "\t 服务端口：" + port);
                return "成功买到商品，库存：" + rest + "\t 服务端口：" + port;
            }
            System.out.println("库存为0/网络故障/不支持多次购买\t 服务端口：" + port);
            return "库存为0/网络故障/不支持多次购买\t 服务端口：" + port;
        }finally {
//            if(redisTemplate.opsForValue().get(REDIS_LOCK).equals(val)){
//                redisTemplate.delete(REDIS_LOCK); // 仍然不是原子操作，还有可能删掉别人的锁，解锁之前过期。
//            }
            /**
             * 不使用lua脚本
             * 使用事务的概念，但是目前测试总是出问题，
             */

            /**
             * redisson 实现
             */

            if(lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
