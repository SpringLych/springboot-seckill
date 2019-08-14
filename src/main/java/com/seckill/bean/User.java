package com.seckill.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lych
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {


    private static final long serialVersionUID = -6792462920730338115L;
    /**
     * 用户id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt)+salt
     */
    private String password;

    /**
     * 混淆盐
     */
    private String salt;

    /**
     * 头像，云存储的ID
     */
    private String head;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;


    /*public static final String ID = "id";

    public static final String NICKNAME = "nickname";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String HEAD = "head";

    public static final String REGISTER_DATE = "register_date";

    public static final String LAST_LOGIN_DATE = "last_login_date";

    public static final String LOGIN_COUNT = "login_count";*/

}
