package com.caiwei.demo.importtest;

import org.springframework.stereotype.Component;

/**
 * @ClassName: ByConfigClass
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 00:59
 */
@Component
public class ByConfigClass1 {

    public void printClassName() {
        System.out.println("类名1:"+Thread.currentThread().getStackTrace()[0].getClassName());
        System.out.println("类名2:"+Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
