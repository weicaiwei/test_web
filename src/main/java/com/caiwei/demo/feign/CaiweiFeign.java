package com.caiwei.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: CaiweiFeign
 * @Description: feignClient demo
 * @auther: caiwei
 * @date: 2019/4/21 22:24
 */

@FeignClient(name = "hello", url = "http://127.0.0.1:80")
public interface CaiweiFeign {

    @RequestMapping(
            value = "/hello",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String hello();

}
