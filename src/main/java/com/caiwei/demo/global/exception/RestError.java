package com.caiwei.demo.global.exception;

import com.caiwei.demo.global.response.Response;
import com.caiwei.demo.global.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: RestError
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/22 23:40
 */

@RestController
@Slf4j
public class RestError implements ErrorController {


    @Autowired
    private Response response;

    @RequestMapping(value="/error")
    public Result restError(HttpServletResponse rs,Exception e){

        if (404 == rs.getStatus()) {
            return response.except("requested method not found. error code:" + rs.getStatus());
        } else {
            return response.except("unknown error. error code:" + rs.getStatus());
        }

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }


}
