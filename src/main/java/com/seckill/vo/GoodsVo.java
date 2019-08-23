package com.seckill.vo;

import com.seckill.bean.Goods;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Setter
@Getter
public class GoodsVo extends Goods {
    private static final long serialVersionUID = 1136167038377827513L;
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Integer version;
}
