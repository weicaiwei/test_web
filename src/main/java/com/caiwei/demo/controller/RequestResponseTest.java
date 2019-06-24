package com.caiwei.demo.controller;


import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


/**
 * @ClassName: RequestResponseTest
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/5/17 00:06
 */
@Controller
@Slf4j
public class RequestResponseTest {

    @RequestMapping("/helloRequestResponse")
    public void requestResponseTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("1：获得客户端信息:");
        log.info("request.getRequestURL(): " + request.getRequestURL().toString());
        log.info("request.getRequestURI(): " + request.getRequestURI());
        log.info("request.getQueryString(): " + request.getQueryString());
        log.info("request.getPathInfo(): " + request.getPathInfo());
        log.info("request.getRemoteAddr(): " + request.getRemoteAddr());
        log.info("request.getRemoteHost(): " + request.getRemoteHost());
        log.info("request.getRemotePort(): " + request.getRemotePort());
        log.info("request.getLocalAddr(): " + request.getLocalAddr());
        log.info("request.getLocalName(): " + request.getLocalName());

        log.info("1：获得客户机请求头:");
        log.info("request.getMethod(): " + request.getMethod());
        log.info("request.getHeaderNames(): " + request.getHeaderNames().toString());
        log.info("request.getHeader(Accept): " + request.getHeader("Accept"));

        OutputStream outputStream = response.getOutputStream();
        String a = "我收到消息了，这是返回给你的数据";
        outputStream.write(a.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

    }
}
