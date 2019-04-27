package com.caiwei.demo.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName: ImportConfig
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 00:55
 */
@Import({GeneralClass.class,ByConfigClass1.class,ByConfigClass2.class,
        ImportSelectorDemo.class,ImportBeanDefinitionRegistrarDemo.class})
@Configuration
public class ImportConfig {

}
