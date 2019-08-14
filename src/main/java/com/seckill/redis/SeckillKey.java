package com.seckill.redis;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
public class SeckillKey extends BasePrefix {
    /**
     * 默认设置0时间，永不过期
     */
    private SeckillKey(String prefix) {
        super(prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey("goods_over");
}
