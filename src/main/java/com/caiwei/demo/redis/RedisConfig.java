package com.caiwei.demo.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisOperations;

import java.time.Duration;

/**
 * @ClassName: RedisConfig
 * @Description: redis connect factory config
 * @auther: caiwei
 * @date: 2019/4/22 12:27
 */
@Configuration
public class RedisConfig {

    // Redis服务器地址
    @Value("${spring.redis.host}")
    private String host;
    // Redis服务器连接端口
    @Value("${spring.redis.port}")
    private Integer port;
    // Redis数据库索引（默认为0）
    @Value("${spring.redis.database}")
    private Integer database;
    // Redis服务器连接密码（默认为空）
    @Value("${spring.redis.password}")
    private String password;
    // 连接超时时间（毫秒）
    @Value("${spring.redis.timeout}")
    private Integer timeout;

    // 连接池最大连接数（使用负值表示没有限制）
    @Value("${spring.redis.lettuce.pool.max-active}")
    private Integer maxTotal;
    // 连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Integer maxWait;
    // 连接池中的最大空闲连接
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;
    // 连接池中的最小空闲连接
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private Integer minIdle;
    // 关闭超时时间
    @Value("${spring.redis.lettuce.shutdown-timeout}")
    private Integer shutdown;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        //单机模式
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setDatabase(database);
        configuration.setPassword(RedisPassword.of(password));
        //哨兵模式
        //RedisSentinelConfiguration configuration1 = new RedisSentinelConfiguration();
        //集群模式
        //RedisClusterConfiguration configuration2 = new RedisClusterConfiguration();

        //缓存连接池相关配置
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWait);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        LettucePoolingClientConfiguration pool = LettucePoolingClientConfiguration.builder()
                .poolConfig(config)
                .commandTimeout(Duration.ofMillis(timeout))
                .shutdownTimeout(Duration.ofMillis(shutdown))
                .build();
        //生成连接工厂
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, pool);
        //factory.setShareNativeConnection(false);//是否允许多个线程操作共用同一个缓存连接，默认true，false时每个操作都将开辟新的连接
        return factory;
    }

    @Autowired
    RedisSerializer fastJsonRedisSerializer;


    // 设置 redisTemplate 序列化方式
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
