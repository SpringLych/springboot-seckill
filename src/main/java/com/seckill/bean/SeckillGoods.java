package com.seckill.bean;

import lombok.Data;

import java.util.Date;

/**
 * 参加秒杀的商品
 *
 * @author LiYingChun
 * @date 2019/8/15
 */
@Data
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private int version;
}
