package com.caiwei.demo.model;

import lombok.Data;

/**
 * @ClassName: User
 * @Description: 用户实体类
 * @auther: caiwei
 * @date: 2019/6/25 23:29
 */
@Data
public class User {

    private String username;
    private String password;
    private String phone;
    private String email;
    private String imageUrl;
    private String lastIp;
    private String lastTime;
}
