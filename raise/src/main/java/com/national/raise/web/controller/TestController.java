package com.national.raise.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test/hello")
    public String test() {
        System.out.println("请求成功！！！");
        // 默认访问templates下面的.html页面
        return "index";
    }
}
