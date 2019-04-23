package com.caiwei.demo.global.response;

import org.springframework.stereotype.Component;

/**
 * @ClassName: ResultUtil 
 * @Description 对接口返回数据进行格式统一
 * @author caiwei
 * @date 2019年1月1日
 */
@Component
public class Response {
    

    private static final String SUCCESS = "1";
    
    private static final String FAIL = "0";
    
    private static final String EXCEPTION = "2";
    

    public Result success(Object data) {
        return new Result(SUCCESS,null,data);
    }

    public Result fail(String msg) {
        return new Result(FAIL,msg,null);
    }

    public Result except(String msg) {
        return new Result(EXCEPTION,msg,null);
    }
    
    


}
