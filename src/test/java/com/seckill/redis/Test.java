package com.seckill.redis;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @org.junit.Test
    public void add(){
        redisTemplate.opsForValue().set("boot", "good");

    }
}
