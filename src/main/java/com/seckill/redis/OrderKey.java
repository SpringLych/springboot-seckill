package com.seckill.redis;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
public class OrderKey extends BasePrefix {

    /**
     * 默认设置0时间，永不过期
     *
     */
    private OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("seckill");
}
