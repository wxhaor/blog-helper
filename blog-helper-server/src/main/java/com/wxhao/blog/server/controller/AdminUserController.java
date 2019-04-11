package com.wxhao.blog.server.controller;


import com.wxhao.blog.server.boot.utils.ConvertUtils;
import com.wxhao.blog.server.pojo.AdminUserVO;
import com.wxhao.blog.server.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/adminUser")
@Slf4j
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;


    @RequestMapping("/randSelectOne")
    public AdminUserVO randSelectOne() {
        AdminUserVO adminUserVO = ConvertUtils.convert(adminUserService.randSelectOne(), AdminUserVO.class);
        return adminUserVO;
    }

}
