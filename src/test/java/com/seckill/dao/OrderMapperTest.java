package com.seckill.dao;

import com.seckill.bean.Order;
import com.seckill.bean.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insert() {
    }

    @Test
    public void getOrderByUserIdGoodsId() {
        long userId = 18718185897L;
        long goodsId = 1L;
        Order order = orderMapper.getOrderByUserIdGoodsId(userId, goodsId);
        log.info("----order {} ----", order.toString());
    }
}