package com.wxhao.blog.server.pojo;

import lombok.Data;

import java.util.Date;

/**
 * vo 基类
 * @author wxhao
 * @date 2018/3/22
 */
@Data
public abstract class BaseVO {

    /**
     * 主键
     */
    private Long id;

    private Date createTime;

    private Date updateTime;

}
