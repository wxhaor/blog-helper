package com.wxhao.blog.server.service;

import com.wxhao.blog.server.mapper.AdminUserMapper;
import com.wxhao.blog.server.po.AdminUser;
import com.wxhao.blog.server.service.base.HaoBaseService;
import org.springframework.stereotype.Service;

/**
 * @author wxhao
 * @date 2018/12/22
 */
@Service
public class AdminUserService extends HaoBaseService<AdminUserMapper, AdminUser> {

    public AdminUser randSelectOne() {
        return baseMapper.randSelectOne();
    }

}
