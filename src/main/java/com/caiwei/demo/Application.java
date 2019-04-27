package com.caiwei.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
@EnableRedisHttpSession
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public HttpMessageConverter  configureMessageConverters() {
		//1.需要定义一个convert转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//2:添加fastJson的配置信息;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				//保留map空的字段
				SerializerFeature.WriteMapNullValue,
				// 将String类型的null转成""
				SerializerFeature.WriteNullStringAsEmpty,
				// 将Number类型的null转成0
				SerializerFeature.WriteNullNumberAsZero,
				// 将List类型的null转成[]
				SerializerFeature.WriteNullListAsEmpty,
				// 将Boolean类型的null转成false
				SerializerFeature.WriteNullBooleanAsFalse,
				// 避免循环引用
				SerializerFeature.DisableCircularReferenceDetect
		);
		//添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);
		//国际化
		fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
		List<MediaType> fastMediaTypes = new ArrayList<>();
		//处理中文乱码问题
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		//4.在convert中添加配置信息.
		fastConverter.setSupportedMediaTypes(fastMediaTypes);

		return fastConverter;
	}

}
