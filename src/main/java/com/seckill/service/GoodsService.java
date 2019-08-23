package com.seckill.service;

import com.seckill.bean.SeckillGoods;
import com.seckill.dao.GoodsMapper;
import com.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
@Service
public class GoodsService {
    /**
     * 乐观锁最大重试次数
     */
    private static final int DEFAULT_MAX_RETRIES = 5;

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 获取商品列表
     */
    public List<GoodsVo> goodsVoList() {
        return goodsMapper.listGoodsVo();
    }

    /**
     * 根据Id查询指定商品
     */
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }


    public boolean reduceStock(GoodsVo goodsVo) {
        int numAttemps = 0;
        int ret = 0;

        SeckillGoods seckillGoods = new SeckillGoods();
        seckillGoods.setGoodsId(goodsVo.getId());
        seckillGoods.setVersion(goodsVo.getVersion());

        do {
            ++numAttemps;
            try {
                seckillGoods.setVersion(goodsMapper.getVersionByGoodsId(goodsVo.getId()));
                ret = goodsMapper.reduceStockByVersion(seckillGoods);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ret != 0) {
                break;
            }
        } while (numAttemps < DEFAULT_MAX_RETRIES);
        return ret > 0;
    }

}
