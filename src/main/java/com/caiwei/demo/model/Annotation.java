package com.caiwei.demo.model;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;

/**
 * @ClassName: Annotation
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/22 17:46
 */
@Data
public class Annotation {

    @NonNull
    private String name;

    @NonNull
    private String love;

    BindingResult bindingResult;

}
