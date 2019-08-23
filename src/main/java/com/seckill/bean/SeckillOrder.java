package com.seckill.bean;

import lombok.Data;

/**
 * 秒杀生成的订单
 *
 * @author LiYingChun
 * @date 2019/8/15
 */
@Data
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
