package com.wxhao.blog.server.mapper;

import com.wxhao.blog.server.mapper.base.HaoBaseMapper;
import com.wxhao.blog.server.po.BlogImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogImageMapper extends HaoBaseMapper<BlogImage> {


    List<BlogImage> findImageByArticleId(Long articleId);

}