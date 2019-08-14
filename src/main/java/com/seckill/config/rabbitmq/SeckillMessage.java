package com.seckill.config.rabbitmq;

import com.seckill.bean.User;
import lombok.Data;

/**
 * @author LiYingChun
 * @date 2019/8/13
 * 消息体
 */
@Data
public class SeckillMessage {
    private User user;
    private long goodsId;
}
