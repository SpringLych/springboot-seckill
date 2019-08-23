package com.seckill.service;

import com.seckill.bean.OrderInfo;
import com.seckill.bean.SeckillOrder;
import com.seckill.bean.User;
import com.seckill.redis.RedisServices;
import com.seckill.redis.SeckillKey;
import com.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 秒杀业务相关
 *
 * @author LiYingChun
 * @date 2019/8/15
 */
@Service
public class SeckillService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisServices redisServices;

    /**
     * 保证 减少库存  下订单  写入秒杀订单 是一个事务
     */
    @Transactional
    public OrderInfo seckill(User user, GoodsVo goodsVo) {

        // 减库存
        boolean success = goodsService.reduceStock(goodsVo);
        if (success) {
            // 下订单 写入订单表
            return orderService.createOrder(user, goodsVo);
        } else {
            // 商品售完
            setGoodsOver(goodsVo.getId());
            return null;
        }
    }

    /**
     * 根据 user id  和 goods id获取秒杀结果
     *
     * @return 成功：秒杀订单的id；商品售完 -1
     */
    public long getSeckillResult(long userId, long goodsId) {
        SeckillOrder order = orderService.getOrderByUserIdGoodsId(userId, goodsId);
        if (order != null) {
            return order.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private boolean getGoodsOver(long goodsId) {
        return redisServices.exists(SeckillKey.isGoodsOver, "" + goodsId);
    }

    /**
     * 更新缓存中秒杀商品售完
     */
    private void setGoodsOver(Long goodsVoId) {
        redisServices.set(SeckillKey.isGoodsOver, "" + goodsVoId, true);
    }
}
