package com.caiwei.demo.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: d
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/22 00:44
 */
@FeignClient(name = "github-client", url = "https://api.github.com")
public interface GitHubFeign {

    @RequestMapping(
            value = "/search/repositories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    String searchRepo(@RequestParam("q") String q);

}
