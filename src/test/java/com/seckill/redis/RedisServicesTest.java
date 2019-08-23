package com.seckill.redis;

import com.seckill.bean.User;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author LiYingChun
 * @date 2019/8/14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServicesTest {

    @Autowired
    private RedisServices redisServices;



    @Test
    public void get() {
    }

    @Test
    public void set() {
        User user = new User();
        user.setNickname("Jon");
        UserKey userKey = UserKey.getById;
        redisServices.set(userKey, "hello", user);
    }
}