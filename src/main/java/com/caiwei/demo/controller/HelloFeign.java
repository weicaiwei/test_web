package com.caiwei.demo.controller;

import com.alibaba.fastjson.JSON;
import com.caiwei.demo.feign.CaiweiFeign;
import com.caiwei.demo.feign.GitHubFeign;
import com.caiwei.demo.global.response.Response;
import com.caiwei.demo.global.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @ClassName: helloFeign
 * @Description: feign使用demo
 * @auther: caiwei
 * @date: 2019/4/21 22:35
 */

@RestController
//检查@RequestParm需要将@Validated写在类上，目前不知道原因
@Validated
public class HelloFeign {

    @Autowired
    private Response response;

    @Autowired
    private CaiweiFeign caiweiFeign;

    @Autowired
    private GitHubFeign gitHubFeign;


    @GetMapping(
            value = "/helloFeign",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Map helloFeign() {

        String hello = caiweiFeign.hello();
        Map map = JSON.parseObject(hello, java.util.HashMap.class);
        return map;
    }

    @GetMapping(value="/hello/{name}")
    /*@ResponseStatus(HttpStatus.OK)*/
    public Result helloFastjson( @Size(min = 3,max = 20, message = "密码长度为6-8位。") @PathVariable("name") String hello) {

        return response.success("{\"h\":\"30\", \"a\":\"母猪的产后护理\"}");
    }

    @RequestMapping(
            value = "/github/search/repositories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    Object searchRepo(@RequestParam("q") String q, @RequestParam("type") String type) {
        if (type.equals("fj")) {
            //处理“带转义/的问题
            return JSON.parse(gitHubFeign.searchRepo(q));
        } else {
            return gitHubFeign.searchRepo(q);
        }
    }
}
