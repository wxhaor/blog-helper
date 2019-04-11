package com.wxhao.blog.server.mapper;

import com.wxhao.blog.server.mapper.base.HaoBaseMapper;
import com.wxhao.blog.server.po.AppUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wxhao
 * @date 2018/12/12
 */
@Mapper
public interface AppUserMapper extends HaoBaseMapper<AppUser> {

    AppUser randSelectOne();

}
