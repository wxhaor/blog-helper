package com.wxhao.blog.server.pojo;

import lombok.Data;


@Data
public class BlogArticleModel extends BaseVO {


    /**
     * 文章名称
     */
    private String articleName;

    private String remoteSrcUrl;
}
