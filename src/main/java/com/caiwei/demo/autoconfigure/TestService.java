package com.caiwei.demo.autoconfigure;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestService
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/5/1 20:02
 */
@Data
public class TestService {

    private String msg;

    public String sayHello() {

        System.out.println("springboot-autoconfigure,hello:" + msg);
        return "hello world! " + msg;
    }
}
