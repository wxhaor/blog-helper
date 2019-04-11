package com.wxhao.blog.server.po;

import com.wxhao.blog.server.po.base.BaseUpdateTimePO;
import lombok.Data;


@Data
public class BlogArticle extends BaseUpdateTimePO {


    /**
     * 文章名称
     */
    private String articleName;

}
