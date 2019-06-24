package com.caiwei.demo.controller;

import com.caiwei.demo.autoconfigure.TestService;
import com.caiwei.demo.global.NowApplicationContext;
import com.caiwei.demo.global.response.Response;
import com.caiwei.demo.global.response.Result;
import com.caiwei.demo.mapper.DepartmentMapper;
import com.caiwei.demo.model.Book;
import com.caiwei.demo.model.Department;
import com.caiwei.demo.mongodb.MongoDBService;
import com.caiwei.demo.quartz.BeforeJob;
import com.caiwei.demo.quartz.QuartzUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableFindOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("url"));
        return map;
    }

    @Autowired
    MongoDBService mongoDBService;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/mongo")
    public Result queryMongo() {


        Book book1 = new Book();
        book1.setIsbn("caiwei");
        book1.setTitle("母猪的产后护理");

        Book book2 = new Book();
        book2.setIsbn("wan");
        book2.setTitle("母猪的产前护理");

        mongoDBService.saveTest(book1);
        mongoDBService.saveTest(book2);
        List<Book> bookList = mongoTemplate.findAll(Book.class);
        return response.success(bookList);

    }

    @Autowired
    TestService testService;

    @GetMapping("autoconfigure")

    public Result helloAutoConfigure() {

        String helloAuto = testService.sayHello();
        return response.success(helloAuto);
    }

    @Autowired
    QuartzUtil quartzUtil;

    @GetMapping("/quartz/{name}/{internal}")
    public Result quartzScheduler(
            @PathVariable("name")String name,
            @PathVariable("internal") Integer internal) {

        boolean b = quartzUtil.addSimpleJob(name, internal, TimeUnit.SECONDS, BeforeJob.class);

        return response.success("成功了");

    }

    @CrossOrigin
    @GetMapping("/induo")
    public List<String> getArrray() {
        List<String> array = new ArrayList<>();
        array.add("sdfdsf");
        array.add("fdsf");
        array.add("dsfds");
        array.add("gfdg");
        array.add("sdfhfhdsf");
        array.add("fgfd");
        array.add("fgsdfsffd");
        array.add("123");
        array.add("3123123");
        return array;
    }
}
