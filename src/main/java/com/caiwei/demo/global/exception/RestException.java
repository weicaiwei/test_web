package com.caiwei.demo.global.exception;

import com.caiwei.demo.global.response.Response;
import com.caiwei.demo.global.response.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * @ClassName: RestException
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/22 23:10
 */
@RestControllerAdvice
@Configuration
public class RestException {

/*    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        return new MethodValidationPostProcessor();
    }*/

    @Autowired
    Response response;

    @ExceptionHandler(Exception.class)
    public Result except(Exception e){
        return response.except(StringUtil.isNullOrEmpty(e.getMessage()) ? e.toString() : e.getMessage());

    }
}


