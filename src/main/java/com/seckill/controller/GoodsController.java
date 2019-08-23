package com.seckill.controller;


import cn.hutool.core.util.StrUtil;
import com.seckill.bean.User;
import com.seckill.common.result.Result;
import com.seckill.redis.GoodsKey;
import com.seckill.redis.RedisServices;
import com.seckill.service.GoodsService;
import com.seckill.vo.GoodsDetailVo;
import com.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    RedisServices redisServices;
    @Autowired
    GoodsService goodsService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;


    /**
     * 商品列表页面
     * 不返回页面，直接返回html代码
     */
    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response,
                       Model model, User user) {

        String html = redisServices.get(GoodsKey.getGoodsList, "", String.class);
        if (!StrUtil.isEmpty(html)) {
            return html;
        }
        List<GoodsVo> goodsVoList = goodsService.goodsVoList();
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsVoList);

        // 缓存中没有数据手动渲染

        WebContext context = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);

        if (!StrUtil.isEmpty(html)) {
            redisServices.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response,
                                        Model model, User user, @PathVariable("goodsId") long goodsId) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);

        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        log.info("startAt: {}, now: {}, start - now = {}", startAt, now, startAt-now);
        int seckillStatus;
        // 秒杀剩余开始时间
        int remainSeconds;


        if (now < startAt) {
            // 秒杀未开始
            seckillStatus = 0;
            remainSeconds = (int) (startAt - now) / 1000;
        } else if (now > endAt) {
            // 秒杀结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            seckillStatus = 1;
            remainSeconds = 0;
        }

        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setSeckillStatus(seckillStatus);
        goodsDetailVo.setRemainSeconds(remainSeconds);
        goodsDetailVo.setGoods(goodsVo);
        goodsDetailVo.setUser(user);

        return Result.success(goodsDetailVo);
    }
}

