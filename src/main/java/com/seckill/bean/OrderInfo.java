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
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 577158047699276469L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long goodsId;

    private Long deliveryAddrId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    /**
     * 订单渠道，1在线，2android，3ios
     */
    private Integer orderChannel;

    /**
     * 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    private Integer status;

    private Date createDate;

    private Date payDate;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String GOODS_ID = "goods_id";

    public static final String DELIVERY_ADDR_ID = "delivery_addr_id";

    public static final String GOODS_NAME = "goods_name";

    public static final String GOODS_COUNT = "goods_count";

    public static final String GOODS_PRICE = "goods_price";

    public static final String ORDER_CHANNEL = "order_channel";

    public static final String STATUS = "status";

    public static final String CREATE_DATE = "create_date";

    public static final String PAY_DATE = "pay_date";

}
