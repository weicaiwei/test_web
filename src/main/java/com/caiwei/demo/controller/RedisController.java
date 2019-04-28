package com.caiwei.demo.controller;

import com.caiwei.demo.global.response.Response;
import com.caiwei.demo.global.response.Result;
import com.caiwei.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RedisController
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 21:47
 */
@RestController
public class RedisController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    Response response;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpSession session;

    @GetMapping("/redis")
    public Result setRedisData() {
        Employee employee = new Employee();
        employee.setId(17600602868L);
        employee.setName("王鹏达");
        employee.setEmail("1750886898@qq.com");
        employee.setGender("nan");
        redisTemplate.opsForValue().set("caiwei", employee);
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("caiwei", redisTemplate.opsForValue().get("caiwei"));
        map.put("session.className", session.getClass().getName());
        return response.success(map);
    }
}
