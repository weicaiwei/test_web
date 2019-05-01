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
    @ConditionalOnMissingBean
    public TestService testService() {

        TestService testService = new TestService();
        testService.setMsg(testProperty.getMsg());
        testService.sayHello();
        return testService;
    }
}
