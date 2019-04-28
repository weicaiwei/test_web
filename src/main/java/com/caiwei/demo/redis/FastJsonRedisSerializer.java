package com.caiwei.demo.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName: FastJsonRedisSerializer
 * @Description: 设置fastjson为redis序列化器
 * @auther: caiwei
 * @date: 2019/4/28 21:28
 */
@Component
public class FastJsonRedisSerializer implements RedisSerializer {


    @Override
    public byte[] serialize(Object o) throws SerializationException {

        if (o == null) {
            return new byte[0];
        }
        return JSON.toJSONString(o, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        return JSON.parse(str);
    }
}
