package com.seckill.common.result;


/**
 * @author LiYingChun
 * @date 2019/8/13
 */
public enum CodeEnum {
    /**
     * code desc
     */
    SUCCESS(0, "成功"),
    SERVER_ERROR(50100, "服务端异常");

    private final int code;
    private final String desc;

    CodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
