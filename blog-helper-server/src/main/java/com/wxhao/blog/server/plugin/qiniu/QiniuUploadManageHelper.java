package com.wxhao.blog.server.plugin.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.StringMap;
import com.wxhao.blog.server.boot.helper.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class QiniuUploadManageHelper {

    public static FetchRet fetch(String remoteSrcUrl) {

        BucketManager bucketManager = new BucketManager(QiniuConfig.getAuth(), QiniuConfig.getConfiguration());
        QiniuConfigProperties configProperties = ApplicationContextHelper.getBean(QiniuConfigProperties.class);
        //抓取网络资源到空间
        try {
            FetchRet fetchRet = bucketManager.fetch(remoteSrcUrl, configProperties.getBucket(), null);
            return fetchRet;

        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
        throw new RuntimeException("上传失败");
    }

    public static DefaultPutRet simpleUpload(InputStream inputStream) {
        //构造一个带指定Zone对象的配置类
        UploadManager uploadManager = new UploadManager(QiniuConfig.getConfiguration());

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        StringMap stringMap = null;
        String mimeType = null;
        String upToken = QiniuConfig.simpleToken();
        try {
            Response response = uploadManager.put(inputStream, key, upToken, stringMap, mimeType);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error(r.toString(), ex);
            try {
                log.error(r.bodyString());
            } catch (QiniuException exBodyString) {
                log.error("QiniuException-Error", exBodyString);
            }
        }
        throw new RuntimeException("上传失败");

    }
}
