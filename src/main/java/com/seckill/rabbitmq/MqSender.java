package com.seckill.rabbitmq;

import com.seckill.redis.RedisServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiYingChun
 * @date 2019/8/16
 */
@Service
@Slf4j
public class MqSender {

    @Autowired
    AmqpTemplate amqpTemplate;


    public void sendTopic(Object message) {
        String msg = RedisServices.beanToString(message);
        log.info("send topic message: {}", message);
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE, MqConfig.TOPIC_KEY1, msg + "1");
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE, MqConfig.TOPIC_KEY2, msg + "2");
    }

    public void sendSeckillMessage(SeckillMessage message){
        String msg = RedisServices.beanToString(message);
        log.info("send seckill message: {}", message);
        amqpTemplate.convertAndSend(MqConfig.QUEUE, msg);
    }
}
