package com.caiwei.demo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * @ClassName: CaCheManager
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/26 00:34
 */
@Configuration
public class CaCheConfig {

    @Autowired
    RedisSerializer redisSerializer;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        //包装成SerializationPair类型
        RedisSerializationContext.SerializationPair serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer);
        //redis默认配置文件,并且设置过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration.entryTtl(Duration.ofHours(1));
        //设置序列化器
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(serializationPair);
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .build();


    }
}
