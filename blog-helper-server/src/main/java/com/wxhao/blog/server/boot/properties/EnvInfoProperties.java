package com.wxhao.blog.server.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wxhao
 * @date 2018/3/12
 */
// 刷新
//@RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "hao.env-info")
public class EnvInfoProperties {

    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 环境名称
     */
    private String envName;

}
