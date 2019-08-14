package com.seckill.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Order implements Serializable {


    private static final long serialVersionUID = -9059090333343183699L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long orderId;

    private Long goodsId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String ORDER_ID = "order_id";

    public static final String GOODS_ID = "goods_id";

}
