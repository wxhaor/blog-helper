package com.wxhao.blog.server.po.base;

import lombok.Data;

import java.util.Date;

/**
 * 实体基类注解
 *
 * @author wxhao
 * @date 2018/1/16
 */

@Data
public abstract class BaseCreateTimePO extends BaseIdPO{

    /**
     * 创建时间
     */
    private Date createTime;

}

