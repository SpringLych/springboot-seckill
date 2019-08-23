package com.seckill.service;

import com.seckill.bean.OrderInfo;
import com.seckill.bean.SeckillOrder;
import com.seckill.bean.User;
import com.seckill.dao.OrderMapper;
import com.seckill.redis.OrderKey;
import com.seckill.redis.RedisServices;
import com.seckill.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author LiYingChun
 * @date 2019/8/15
 */
@Service
public class OrderService {

    final
    OrderMapper orderMapper;

    final
    RedisServices redisServices;

    public OrderService(OrderMapper orderMapper, RedisServices redisServices) {
        this.orderMapper = orderMapper;
        this.redisServices = redisServices;
    }

    /**
     * 通过用户ID和商品ID获取订单
     */
    public SeckillOrder getOrderByUserIdGoodsId(long userId, long goodsId) {
        return redisServices.get(OrderKey.getSeckillOrderByUidGid,
                "" + userId + "_" + goodsId, SeckillOrder.class);
    }

    /**
     * 通过订单id获取订单信息
     */
    public OrderInfo getOrderById(long orderId) {
        return orderMapper.getOrderById(orderId);
    }

    /**
     * 分别在订单详情表和秒杀订单表增加一条数据，保证这两个操作时同一事务
     *
     * @return 订单详情
     */
    public OrderInfo createOrder(User user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());

        orderMapper.insert(orderInfo);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(user.getId());

        orderMapper.insertSeckillOrder(seckillOrder);

        redisServices.set(OrderKey.getSeckillOrderByUidGid,
                "" + user.getId() + "_" + goodsVo.getId(), seckillOrder);

        return orderInfo;
    }
}
