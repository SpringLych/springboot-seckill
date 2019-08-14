package com.seckill.vo;

import com.seckill.common.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author LiYingChun
 * @date 2019/8/14
 */
@Data
public class LoginVo {

    /**
     * 因为框架没有校验手机格式注解，所以自己定义
     */
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;

}
