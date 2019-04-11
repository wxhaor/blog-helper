package com.wxhao.blog.server.po;

import com.wxhao.blog.server.po.base.BaseCreateTimePO;
import lombok.Data;

/**
 * $tableComment
 */
@Data
public class BlogImage extends BaseCreateTimePO {

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 文章名称
     */
    private String articleName;

    /**
     * 图片(七牛)key
     */
    private String imageKey;

    /**
     * 图片(七牛)hash值
     */
    private String imageHash;

    /**
     * 图片名称
     */
    private String imageName;

}
