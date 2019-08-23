package com.seckill.rabbitmq;

import com.seckill.bean.User;
import lombok.Data;

/**
 * @author LiYingChun
 * @date 2019/8/13
 * 秒杀信息 消息体
 */
@Data
public class SeckillMessage {
    private User user;
    private long goodsId;
}
