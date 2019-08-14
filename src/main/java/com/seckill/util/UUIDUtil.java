package com.seckill.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
public class UUIDUtil {
    /**
     * 返回字符串类型的随机UUID类似a5c8a5e8-df2b-4706-bea4-08d0939410e3
     */
    public static String uuid() {
        return IdUtil.randomUUID();
    }
}
