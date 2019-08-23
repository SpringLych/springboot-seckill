package com.seckill.service;

import cn.hutool.core.util.StrUtil;
import com.seckill.bean.User;
import com.seckill.common.exception.GlobalException;
import com.seckill.common.result.CodeMsg;
import com.seckill.dao.UserMapper;
import com.seckill.redis.RedisServices;
import com.seckill.redis.UserKey;
import com.seckill.util.MD5Util;
import com.seckill.util.UUIDUtil;
import com.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisServices redisServices;

    public static final String COOKIE_NAME_TOKEN = "token";

    public User getById(Long id) {
        // 通过缓存获取对象
        User user = redisServices.get(UserKey.getById, "" + id, User.class);
        if (user != null) return user;
        // 缓存中没有 使用数据库
        user = userMapper.selectById(id);
        // 存入缓存
        if (user != null) {
            redisServices.set(UserKey.getById, "" + id, User.class);
        }
        return user;
    }

    /**
     * 缓存同步场景：更新密码
     */
    public boolean updatePassword(String token, long id, String formPass) {
        User user = getById(id);
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 更新数据库
        User toBeUpdate = new User();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        userMapper.updateById(user);
        // 更新缓存  先删除再插入
        redisServices.delete(UserKey.getById, "" + id);
        user.setPassword(toBeUpdate.getPassword());
        redisServices.set(UserKey.token, token, user);
        return true;
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        // 判断手机号是否存在
        User user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成唯一id作为token
        String token = UUIDUtil.uuid();
        // 延长有效期
        addCookie(response, token, user);
        return token;
    }

    /**
     * 将token作为key，用户信息作为value存入redis模拟session
     * 同时将token存入cookie，保存登录状态
     */
    private void addCookie(HttpServletResponse response, String token, User user) {
        redisServices.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 根据token获取用户信息
     */
    public User getByToken(HttpServletResponse response, String token) {
        if (StrUtil.isEmpty(token)) return null;
        User user = redisServices.get(UserKey.token, token, User.class);
        // 延长有效期 有效期等于最后一次操作+有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }


}
