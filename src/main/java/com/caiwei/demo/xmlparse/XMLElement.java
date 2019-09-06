package com.caiwei.demo.xmlparse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: XMLElement
 * @Description: 标注在javaBean中，如果xml节点名称与object属性名称一致，那么标不标皆可
 *  如果不一致，那么需要在想要赋值hide
 * @auther: caiwei
 * @date: 2019/9/4 09:32
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XMLElement {

    String value();
}


