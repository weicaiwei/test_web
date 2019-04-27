package com.caiwei.demo.importtest;

import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ByConfigClass2
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 00:59
 */
@Configuration
public class ByConfigClass2 {

    public void printClassName() {
        System.out.println("类名1:"+Thread.currentThread().getStackTrace()[0].getClassName());
        System.out.println("类名2:"+Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
