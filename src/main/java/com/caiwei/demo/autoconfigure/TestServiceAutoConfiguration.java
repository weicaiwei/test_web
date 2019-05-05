package com.caiwei.demo.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: TestSerivceAutoConfiguration
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/5/1 20:05
 */
@Configuration
@EnableConfigurationProperties(TestProperty.class)
@ConditionalOnClass(TestService.class)
public class TestServiceAutoConfiguration {

    @Autowired
    TestProperty testProperty;

    @Bean
    @ConditionalOnMissingBean(name = "testService")
    public TestService testService() {
        System.out.println("未验证参数");
        TestService testService = new TestService();
        testService.setMsg(testProperty.getMsg());
        testService.setName(testProperty.getName());
        testService.sayHello();
        return testService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "testService2")
    //@ConditionalOnProperty要求必须在配置文件中配置了name=havingValue的参数才行，
    //如果没有在配置文件中定义此参数，即使在Property类中的默认值与条件中的相等，那么也不会生成bean成功
    @ConditionalOnProperty(name = "msg",havingValue = "springboot-no")
    public TestService testService2() {
        System.out.println("验证了参数");
        TestService testService2 = new TestService();
        return testService2;
    }

}
