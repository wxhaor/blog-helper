package com.wxhao.blog.server.plugin.qiniu;

import lombok.Data;

@Data
public class PutPolicy {

    private String callbackUrl;
    private String callbackBody;
    private String callbackHost;
    private String callbackBodyType;
    private String callbackFetchKey;
    private String returnUrl;
    private String returnBody;
    private String endUser;
    private String saveKey;
    private String insertOnly;
    private String isPrefixalScope;
    private String detectMime;
    private String mimeLimit;
    private String fsizeLimit;
    private String fsizeMin;
    private String persistentOps;
    private String persistentNotifyUrl;
    private String persistentPipeline;
    private String deleteAfterDays;
    private String fileType;

}
