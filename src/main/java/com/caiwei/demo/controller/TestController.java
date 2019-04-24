package com.caiwei.demo.controller;

import com.caiwei.demo.global.NowApplicationContext;
import com.caiwei.demo.global.response.Response;
import com.caiwei.demo.global.response.Result;
import com.caiwei.demo.mapper.DepartmentMapper;
import com.caiwei.demo.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/23 09:19
 */
@RestController
public class TestController {

    @Autowired
    private Response response;

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/context/{type}")
    public String queryContext(@PathVariable("type") String type) {
        switch (type) {
            case "beanName":
                return NowApplicationContext.get("consumer").toString();
            case "contextName":
                return NowApplicationContext.get();
            default:
                return "啦啦啦啦啦啦啦啦啦";
        }
    }

    @GetMapping("/one/many")
    public Result queryDepartment(Integer id) {
        Department department = departmentMapper.queryDepartment(id);
        return response.success(department);
    }
}
