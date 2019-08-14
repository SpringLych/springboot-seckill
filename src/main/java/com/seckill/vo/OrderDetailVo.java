package com.seckill.vo;

import com.seckill.bean.OrderInfo;
import lombok.Data;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
}
