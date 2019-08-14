package com.seckill.redis;

/**
 * @author LiYingChun
 * @date 2019/8/13
 * 4. 构造接口
 */
public interface KeyPrefix {

    /**
     * 有效期
     */
    int expireSeconds();

    /**
     * 前缀
     */
    String getPrefix();
}
