package com.caiwei.demo.importtest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName: ImportSelectorDemo
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/28 01:05
 */
public class ImportSelectorDemo implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("执行了ImportSelectorDemo");
        return new String[]{"com.caiwei.demo.importtest.ByImportSelectorClass"};
    }
}
