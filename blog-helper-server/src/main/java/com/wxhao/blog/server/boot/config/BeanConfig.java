package com.wxhao.blog.server.boot.config;


import com.wxhao.blog.server.boot.component.SnowflakeIdWorker;
import com.wxhao.blog.server.boot.properties.SnowflakeProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wxhao
 * @date 2018/3/15
 */
@Component
public class BeanConfig {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(SnowflakeProperties properties) {
        return new SnowflakeIdWorker(properties.getWorkerId(), properties.getDataCenterId());
    }


}
