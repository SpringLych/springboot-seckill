package com.seckill.dao;

import com.seckill.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectId() {
        long id = 18181818181L;
        User user = userMapper.selectById(id);
        log.info("-----用户：{}-----", user.toString());
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(223L);
        user.setNickname("JK");

        int res = userMapper.update(user, null);
        log.info("---更新成功 {} 条----", res);
    }
}