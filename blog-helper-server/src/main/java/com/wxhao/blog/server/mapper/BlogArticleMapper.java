package com.wxhao.blog.server.mapper;

import com.wxhao.blog.server.mapper.base.HaoBaseMapper;
import com.wxhao.blog.server.po.BlogArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogArticleMapper extends HaoBaseMapper<BlogArticle> {

    List<BlogArticle> findByName(String name);

}