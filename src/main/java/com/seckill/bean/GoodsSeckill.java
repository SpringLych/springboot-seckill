package com.seckill.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsSeckill implements Serializable {


    private static final long serialVersionUID = 2707332231604687423L;
    /**
     * 秒杀商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;

    /**
     * 并发版本控制
     */
    private Integer version;


    public static final String ID = "id";

    public static final String GOODS_ID = "goods_id";

    public static final String SECKILL_PRICE = "seckill_price";

    public static final String STOCK_COUNT = "stock_count";

    public static final String START_DATE = "start_date";

    public static final String END_DATE = "end_date";

    public static final String VERSION = "version";

}
