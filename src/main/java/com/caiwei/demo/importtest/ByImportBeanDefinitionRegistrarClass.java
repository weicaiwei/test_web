package com.caiwei.demo.importtest;

/**
 * @ClassName: ByImportBeanDefinitionRegistrarClass
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 01:11
 */
public class ByImportBeanDefinitionRegistrarClass {

    public void printClassName() {
        System.out.println("类名1:"+Thread.currentThread().getStackTrace()[0].getClassName());
        System.out.println("类名2:"+Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
