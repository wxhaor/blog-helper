package com.wxhao.blog.server.plugin.qiniu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("qn")
public class QiniuConfigProperties {

    String accessKey = "";
    String secretKey = "";
    String bucket = "";
    String zoneName = "";

    String urlPrefix = "http://img-everyday.wxhaor.com/";
}
