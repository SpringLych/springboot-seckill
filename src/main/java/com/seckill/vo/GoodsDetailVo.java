package com.seckill.vo;

import com.seckill.bean.User;
import lombok.Data;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Data
public class GoodsDetailVo {
    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private User user;
}
