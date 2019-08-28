package com.caiwei.demo.security;

import lombok.Data;

/**
 * @ClassName: UserNamePassword
 * @Description: 封装的用户名称和密码，filter使用
 * @auther: caiwei
 * @date: 2019/6/16 14:40
 */
@Data
public class UsernamePassword {
    private String username;
    private String password;
}
