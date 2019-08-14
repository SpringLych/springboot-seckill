package com.seckill.dao;

import com.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author LiYingChun
 * @date 2019/8/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void listGoodsVo() {
    }

    @Test
    public void getGoodsVoByGoodsId() {
        GoodsVo goodsVo = goodsMapper.getGoodsVoByGoodsId(1);
        log.info("goodsVo: {}", goodsVo);
    }

    @Test
    public void reduceStockByVersion() {
    }

    @Test
    public void getVersionByGoodsId() {
        int version = goodsMapper.getVersionByGoodsId(1);
        log.info("version: {} ", version);
    }
}