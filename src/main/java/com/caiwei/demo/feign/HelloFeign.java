package com.caiwei.demo.feign;

import com.alibaba.fastjson.JSON;
import com.caiwei.demo.feign.CaiweiFeign;
import com.caiwei.demo.feign.GitHubFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: helloFeign
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/21 22:35
 */

@RestController
public class HelloFeign {

    @Autowired
    CaiweiFeign caiweiFeign;

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

    @GetMapping("/hello")
    public String helloFastjson() {
        return "{\"h\":\"30\", \"a\":\"母猪的产后护理\"}";
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
