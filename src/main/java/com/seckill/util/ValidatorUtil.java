package com.seckill.util;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiYingChun
 * @date 2019/8/14
 * 登录校检工具类
 */
public class ValidatorUtil {
    /**
     * 默认以1开头后面加10个数字为手机号
     */
    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StrUtil.isEmpty(src)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(src);
        return m.matches();
    }
}
