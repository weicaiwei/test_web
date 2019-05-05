package com.caiwei.demo.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName: TestProperty
 * @Description: 自动配置的参数默认值类
 * @auther: caiwei
 * @date: 2019/5/1 19:51
 */
@Data
@ConfigurationProperties("auto-test")
public class TestProperty {

    private static final String MSG = "springboot-no";

    private String msg = MSG;

    private String name = "caiwei";

}
