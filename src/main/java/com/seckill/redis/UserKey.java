package com.seckill.redis;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
public class UserKey extends BasePrefix {
    /**
     * 默认两天
     */
    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    /**
     * private 防止被外面实例化
     */
    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 根据token做缓存
     */
    public static UserKey token = new UserKey(TOKEN_EXPIRE, "token");
    /**
     * user key是根据ID做缓存，永不过期
     */
    public static UserKey getById = new UserKey(0, "id");
}
