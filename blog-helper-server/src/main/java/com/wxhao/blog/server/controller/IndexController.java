package com.wxhao.blog.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxhao
 * @date 2019/4/8
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "server is running...";
    }


}
