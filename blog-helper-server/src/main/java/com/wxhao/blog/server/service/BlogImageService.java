package com.wxhao.blog.server.service;

import com.wxhao.blog.server.mapper.BlogImageMapper;
import com.wxhao.blog.server.po.BlogImage;
import com.wxhao.blog.server.service.base.HaoBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxhao
 * @date 2019/3/24
 */
@Service
public class BlogImageService extends HaoBaseService<BlogImageMapper, BlogImage> {


    public List<BlogImage> findImageByArticleId(Long articleId){

        return baseMapper.findImageByArticleId(articleId);
    }

}
