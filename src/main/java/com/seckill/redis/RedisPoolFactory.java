package com.seckill.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author LiYingChun
 * @date 2019/8/13
 * 2. 构建redis pool
 */
@Service
@Slf4j
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;

    /**
     * 将redis连接池注入spring容器
     */
    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisConfig.getPoolMaxIdle());
        config.setMaxTotal(redisConfig.getPoolMaxTotal());
        config.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        log.info("connect redis: host: {}, port: {}", redisConfig.getHost(), redisConfig.getPort());
        return jp;
    }
}
