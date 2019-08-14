package com.seckill.redis;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
public class GoodsKey extends BasePrefix {
    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "goods_list");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "goods_detail");
    public static GoodsKey getGoodsStock = new GoodsKey(60, "goods_stock");
}
