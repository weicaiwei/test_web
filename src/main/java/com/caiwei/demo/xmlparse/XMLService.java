package com.caiwei.demo.xmlparse;

import lombok.extern.java.Log;
import org.dom4j.Element;

import java.lang.reflect.Field;

/**
 * @ClassName: XMLService
 * @Description: 解析xml节点(包含跟javabean属性相同节点的（相对于这些节点的）父节点)为javaBean
 * @auther: caiwei
 * @date: 2019/9/3 23:44
 */
@Log
public class XMLService {

    public <T> T parseXmlElementToJavaBean(Element element, Class<T> tClass) {
        T tObject = null;
        try {
            tObject = tClass.newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String elementValue;
                if (field.isAnnotationPresent(XMLElement.class)) {
                    XMLElement xmlElement = field.getAnnotation(XMLElement.class);
                    elementValue = element.elementText(xmlElement.value());
                }else {
                    elementValue = element.elementText(field.getName());
                }
                if (null != elementValue) {
                    field.set(tObject, elementValue);
                } else {
                    log.info("field:"+field.getName()+"没有找到对应的xml节点");
                }


            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return tObject;
    }
}
