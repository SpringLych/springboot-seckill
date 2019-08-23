package com.seckill.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LiYingChun
 * @date 2019/8/13
 * 1. 读取配置文件的redis信息
 * prefix表示读取所有以redis开头的配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private String password;
    private int port;
    private int timeout;
    private int poolMaxIdle;
    private int poolMaxWait;
    private int poolMaxTotal;
}
