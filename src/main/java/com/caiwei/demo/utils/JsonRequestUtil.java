package com.caiwei.demo.utils;



import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * @ClassName: RequestJsonUtil
 * @Description: 在filter中无法通过注解直接获取post请求中的json数据，该辅助类可以获得
 * @auther: caiwei
 * @date: 2019/6/16 14:03
 */
public class JsonRequestUtil {

    private static final String HTTP_METHOD_GET ="GET";
    private static final String HTTP_METHOD_POST ="POST";

    public static <T> T getJsonRequestObject(HttpServletRequest request, Class<T> tClass) throws IOException {
        String json = getRequestString(request);
        return JSON.parseObject(json, tClass);
    }
    /**
     * 获取 request 中 json 字符串的内容
     * @param: request
     * @return: String
     * @throws: IOException
     */
    public static String getRequestString(HttpServletRequest request)
            throws IOException {
        String submitMethod = request.getMethod();
        // GET
        if (submitMethod.equalsIgnoreCase(HTTP_METHOD_GET)) {
            return new String(request.getQueryString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).replaceAll("%22", "\"");
            // POST
        } else if (submitMethod.equalsIgnoreCase(HTTP_METHOD_POST)){
            return getRequestPostString(request);
        }
        return null;
    }
    /**
     * 获取 post 请求内容
     * @param: request
     * @return: String
     * @throws: IOException
     */
    public static String getRequestPostString(HttpServletRequest request)
            throws IOException {
        byte[] buffer = getRequestPostBytes(request);
        if (buffer == null || buffer.length == 0) {
            throw new IOException();
        }
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = StandardCharsets.UTF_8.name();
        }
        return new String(buffer, charEncoding);
    }

    /**
     * 获取 request 中 byte数组
     * @param: request
     * @return: byte[]
     * @throws: IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            int readLen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readLen == -1) {
                break;
            }
            i += readLen;
        }
        return buffer;
    }
}
