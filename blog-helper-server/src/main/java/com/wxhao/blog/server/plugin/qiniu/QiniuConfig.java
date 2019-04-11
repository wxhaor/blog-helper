package com.wxhao.blog.server.plugin.qiniu;


import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.wxhao.blog.server.boot.helper.ApplicationContextHelper;
import com.wxhao.blog.server.boot.utils.EnumUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * https://developer.qiniu.com/kodo/sdk/1239/java
 * 提供生成客户端上传所需的上传凭证的功能
 * 提供文件从服务端直接上传七牛的功能
 * 提供对七牛空间中文件进行管理的功能
 * 提供对七牛空间中文件进行处理的功能
 * 提供七牛CDN相关的刷新，预取，日志功能
 */
@NoArgsConstructor
public class QiniuConfig {

    /**
     * 服务端SDK在上传方面主要提供两种功能，一种是生成客户端上传所需要的上传凭证，另外一种是直接上传文件到云端。
     * <p>
     * 客户端上传凭证
     * 客户端（移动端或者Web端）上传文件的时候，需要从客户自己的业务服务器获取上传凭证，而这些上传凭证是通过服务端的SDK来生成的，
     * 然后通过客户自己的业务API分发给客户端使用。根据上传的业务需求不同，七牛云Java SDK支持丰富的上传凭证生成方式。
     */


    public static Auth getAuth() {
        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
        Auth auth = Auth.create(configProperties.getAccessKey(), configProperties.getSecretKey());
        return auth;
    }

    /**
     * 简单上传的凭证
     * 最简单的上传凭证只需要AccessKey，SecretKey和Bucket就可以。
     */
    public static String simpleToken() {
        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
        Auth auth = getAuth();
        return auth.uploadToken(configProperties.getBucket());
    }

    /**
     * 覆盖上传的凭证
     * 覆盖上传除了需要简单上传所需要的信息之外，还需要想进行覆盖的文件名称，这个文件名称同时可是客户端上传代码中指定的文件名，两者必须一致
     */
    public static String coverToken(String fileKey) {
        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
        Auth auth = getAuth();
        return auth.uploadToken(configProperties.getBucket(), fileKey);
    }


    /**
     * 自定义上传回复的凭证
     * 默认情况下，文件上传到七牛之后，在没有设置returnBody或者回调相关的参数情况下，七牛返回给上传端的回复格式为hash和key，例如：
     * {"hash":"Ftgm-CkWePC9fzMBTRNmPMhGBcSV","key":"qiniu.jpg"}
     */
//    public void customToken() {
//        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
//        String bucket = configProperties.getBucket();
//        Auth auth = getAuth();
//        StringMap putPolicy = new StringMap();
//        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
//        long expireSeconds = 3600;
//        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
//        System.out.println(upToken);
//    }

    /**
     * 带回调业务服务器的凭证
     * 上面生成的自定义上传回复的上传凭证适用于上传端（无论是客户端还是服务端）和七牛服务器之间进行直接交互的情况下。在客户端上传的场景之下，有时候客户端需要在文件上传到七牛之后，从业务服务器获取相关的信息，这个时候就要用到七牛的上传回调及相关回调参数的设置。
     */
//    public void callbackToken() {
//        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
//        String bucket = configProperties.getBucket();
//        Auth auth = getAuth();
//        StringMap putPolicy = new StringMap();
//        putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
//        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
//        putPolicy.put("callbackBodyType", "application/json");
//        long expireSeconds = 3600;
//        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
//        System.out.println(upToken);
//    }

    /**
     * 带数据处理的凭证
     * 七牛支持在文件上传到七牛之后，立即对其进行多种指令的数据处理，这个只需要在生成的上传凭证中指定相关的处理参数即可。
     */
//    public void dataDealToken() {
//        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
//        String bucket = configProperties.getBucket();
//        Auth auth = getAuth();
//
//        StringMap putPolicy = new StringMap();
//        //数据处理指令，支持多个指令
//        String saveMp4Entry = String.format("%s:avthumb_test_target.mp4", bucket);
//        String saveJpgEntry = String.format("%s:vframe_test_target.jpg", bucket);
//        String avthumbMp4Fop = String.format("avthumb/mp4|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
//        String vframeJpgFop = String.format("vframe/jpg/offset/1|saveas/%s", UrlSafeBase64.encodeToString(saveJpgEntry));
//        //将多个数据处理指令拼接起来
//        String persistentOpfs = StringUtils.join(new String[]{
//                avthumbMp4Fop, vframeJpgFop
//        }, ";");
//        putPolicy.put("persistentOps", persistentOpfs);
//        //数据处理队列名称，必填
//        putPolicy.put("persistentPipeline", "mps-pipe1");
//        //数据处理完成结果通知地址
//        putPolicy.put("persistentNotifyUrl", "http://api.example.com/qiniu/pfop/notify");
//        long expireSeconds = 3600;
//        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
//        System.out.println(upToken);
//    }


    /**
     * 存储区域	地域简称	上传域名
     * 华东   	z0  	服务器端上传：http(s)://up.qiniup.com     客户端上传： http(s)://upload.qiniup.com
     * 华北	    z1	    服务器端上传：http(s)://up-z1.qiniup.com     客户端上传：http(s)://upload-z1.qiniup.com
     * 华南	    z2	    服务器端上传：http(s)://up-z2.qiniup.com     客户端上传：http(s)://upload-z2.qiniup.com
     * 北美	    na0	    服务器端上传：http(s)://up-na0.qiniup.com     客户端上传：http(s)://upload-na0.qiniup.com
     * 东南亚	as0	    服务器端上传：http(s)://up-as0.qiniup.com     客户端上传：http(s)://upload-as0.qiniup.com
     */
    public static Configuration getConfiguration() {
        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
        String zoneName = configProperties.getZoneName();
        ZoneEnum zoneEnum = EnumUtils.getEnum(ZoneEnum.class, objEnum -> objEnum.getName().equals(zoneName));
        Configuration cfg = new Configuration(zoneEnum.getZone());
        return cfg;
    }


    @AllArgsConstructor
    @Getter
    enum ZoneEnum {
        z0("z0", Zone.zone0()),
        z1("z1", Zone.zone1()),
        z2("z2", Zone.zone2()),
        ;
        private String name;

        private Zone zone;


    }

}
