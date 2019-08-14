package com.seckill.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Data
public class GoodsVo {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Integer version;
}
