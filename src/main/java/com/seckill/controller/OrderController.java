package com.seckill.controller;


import com.seckill.bean.OrderInfo;
import com.seckill.bean.User;
import com.seckill.common.result.CodeMsg;
import com.seckill.common.result.Result;
import com.seckill.dao.OrderMapper;
import com.seckill.service.GoodsService;
import com.seckill.vo.GoodsVo;
import com.seckill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单页面
 *
 * @author lych
 * @since 2019-08-13
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> detail(Model model, User user, @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        OrderInfo orderInfo = orderMapper.getOrderById(orderId);
        if (orderInfo == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = orderInfo.getGoodsId();
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo detailVo = new OrderDetailVo();
        detailVo.setGoods(goodsVo);
        detailVo.setOrder(orderInfo);
        return Result.success(detailVo);
    }
}

