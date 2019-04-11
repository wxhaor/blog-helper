package com.wxhao.blog.server.po;

import com.wxhao.blog.server.po.base.BaseCreateTimePO;
import lombok.Data;

/**
 * @author wxhao
 * @date 2018/12/12
 */
@Data
public class AppUser extends BaseCreateTimePO {

    private String username;

    private String password;

    private String mobile;

    private String email;

}
