package com.seckill.common.exception;

import com.seckill.common.result.CodeMsg;
import lombok.Getter;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Getter
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 6234569341638967884L;

    private CodeMsg msg;

    public GlobalException(CodeMsg msg) {
        super(msg.toString());
        this.msg = msg;
    }

}
