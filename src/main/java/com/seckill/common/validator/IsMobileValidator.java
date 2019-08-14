package com.seckill.common.validator;

import cn.hutool.Hutool;
import cn.hutool.core.util.StrUtil;
import com.seckill.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author LiYingChun
 * @date 2019/8/14
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StrUtil.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }

        }
    }
}
