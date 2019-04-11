package com.wxhao.blog.server.service;

import com.wxhao.blog.server.mapper.BlogArticleMapper;
import com.wxhao.blog.server.po.BlogArticle;
import com.wxhao.blog.server.service.base.HaoBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxhao
 * @date 2019/3/24
 */
@Service
public class BlogArticleService extends HaoBaseService<BlogArticleMapper, BlogArticle> {


    public List<BlogArticle> findByName(String name) {
        return baseMapper.findByName(name);
    }

}
