package com.seckill.dao;

import com.seckill.bean.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * @return list goodsVO
     */
    @Select("select g.*, gs.stock_count, gs.start_date, gs.end_date, gs.secill_price, gs.version " +
            "from goods_seckill gs left join goods g on gs.goods_id = g.id")
    List<GoodsVo> listGoodsVo();

    @Select("select g.*, gs.stock_count, gs.start_date, gs.end_date, gs.seckill_price, gs.version " +
            "from goods_seckill gs left join goods g on gs.goods_id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    /**
     * stock_count > 0 和version实现乐观锁，防止超卖
     *
     * @param goods
     */
    @Update("update goods_seckill set stock_count = stock_count - 1, version = version+1" +
            "where goodsId= #{goodsId} and stock_count > 0 and version = #{version}")
    int reduceStockByVersion(Goods goods);


    /**
     * 根据goodsId获得最新版本号
     *
     * @return 最新版本号
     */
    @Select("select version from goods_seckill where goods_id = #{goodsId}")
    int getVersionByGoodsId(@Param("goodsId") long goodsId);
}
