
package com.caiwei.demo.global;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: NowApplicationContext
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/23 01:04
 */
@Component
public class NowApplicationContext implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext thisApplicationContext) throws BeansException {
        applicationContext = thisApplicationContext;
    }

    //获取applicationContext的名字
    public static String get() {
        return "DisplayName:"+applicationContext.getDisplayName()+"   ApplicationName:"+applicationContext.getApplicationName();
    }

    //通过名称获取bean
    public static Object get(String name) {
        return applicationContext.getBean(name);
    }


    //通过类型获取bean
    public static Object get(Class<?> clazz) {
        return applicationContext.getBean(clazz);
    }

    //判断某个bean是不是存在
    public static boolean has(String name) {
        return applicationContext.containsBean(name);
    }

}
