package com.seckill.dao;

import com.seckill.bean.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.bean.OrderInfo;
import com.seckill.bean.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
@Service
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 通过@SelectKey使insert成功后返回主键id，也就是订单id
     *
     * @param orderInfo
     * @return
     */
    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    /**
     * 根据 userId和goodsId查询订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Select("select * from orders where user_id = #{userId} and goods_id = #{goodsId}")
    Order getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    //insert mbp已实现

    // getOrderById -> selectById
    @Select("select * from order_info where id = #{orderId}")
    OrderInfo getOrderById(long orderId);

    /**
     * 将秒杀订单存入订单表
     */
    @Insert("insert into orders (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertSeckillOrder(SeckillOrder order);


}
