package com.seckill.rabbitmq;

import com.seckill.bean.SeckillOrder;
import com.seckill.bean.User;
import com.seckill.redis.RedisServices;
import com.seckill.service.GoodsService;
import com.seckill.service.OrderService;
import com.seckill.service.SeckillService;
import com.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiYingChun
 * @date 2019/8/16
 */
@Service
@Slf4j
public class MqReceiver {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    @RabbitListener(queues = MqConfig.QUEUE)
    public void receiveSeckillMsg(String msg) {
        log.info("receive message: {}" + msg);
        SeckillMessage m = RedisServices.stringToBean(msg, SeckillMessage.class);
        User user = m.getUser();
        long goodsId = m.getGoodsId();

        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goodsVo.getStockCount();
        if (stock <= 0) return;

        // 判断重复秒杀
        SeckillOrder order = orderService.getOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }
        // 减库存 下订单 写入秒杀订单
        seckillService.seckill(user, goodsVo);
    }
}
