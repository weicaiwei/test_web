package com.caiwei.demo.security;

import com.caiwei.demo.utils.JWTUtil;
import com.caiwei.demo.utils.JsonRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: JWTLoginFilter
 * @Description: jwt认证过滤器
 * @auther: caiwei
 * @date: 2019/6/24 23:24
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        return this.getAuthenticationManager().authenticate(getAuthRequest(request));
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain filterChain, Authentication authentication) {

        String token = JWTUtil.build((String) authentication.getPrincipal());
        response.setHeader("Authorization", "Bearer " + token);

    }

    //自动处理前端json或者表单提交来的登录数据
    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authRequest;
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            UsernamePassword usernamePassword;
            try {
                usernamePassword = JsonRequestUtil.getJsonRequestObject(request, UsernamePassword.class);
                authRequest = new UsernamePasswordAuthenticationToken(Objects.requireNonNull(usernamePassword).getUsername(), usernamePassword.getPassword());

            } catch (IOException e) {
                authRequest = new UsernamePasswordAuthenticationToken("", "");
                e.printStackTrace();
            }
            //用户密码通过表单格式传送过来
        } else {
            //用super的方法会导致login回调没有返回值，目前不知道原因,所以直接把父类的方法拷过来
            String username = obtainUsername(request);
            String password = obtainPassword(request);
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            authRequest = new UsernamePasswordAuthenticationToken(username, password);
        }
        return authRequest;
    }
}
