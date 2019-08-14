package com.seckill.redis;

/**
 * @author LiYingChun
 * @date 2019/8/13
 * 4. 实现KeyPrefix接口
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 默认设置0时间，永不过期
     */
    public BasePrefix(String prefix) {
        // 默认0 永不过期
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName() + ":" + prefix;
    }
}
