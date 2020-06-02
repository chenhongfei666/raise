package com.national.raise.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaiseController {

    @GetMapping("/raise/upload")
    public String upload() {
        System.out.println("请求成功！！！");
        // 默认访问templates下面的.html页面
        return "index";
    }

}
