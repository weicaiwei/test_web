package com.caiwei.demo.importtest;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName: ImportBeanDefinitionRegistrarDemo
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 01:12
 */
public class ImportBeanDefinitionRegistrarDemo implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition root = new RootBeanDefinition(ByImportBeanDefinitionRegistrarClass.class);
        registry.registerBeanDefinition("caiwei", root);

    }
}
