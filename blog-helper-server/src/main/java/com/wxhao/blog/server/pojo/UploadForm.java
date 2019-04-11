package com.wxhao.blog.server.pojo;

import lombok.Data;

/**
 * @author wxhao
 * @date 2019/4/6
 */
@Data
public class UploadForm {

    private String articleName;

    private Long id;

    private String remoteSrcUrl;


}
