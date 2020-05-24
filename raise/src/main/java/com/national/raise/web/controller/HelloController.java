package com.national.raise.web.controller;

import com.national.raise.web.pojo.MonthData;
import com.national.raise.web.service.MonthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping
    public String hello() {
        return "国奶养育";
    }

    @Autowired
    private MonthDataService monthDataService;

    /*** 根据id获取用户 * @param id 用户id * @return 用户 */
    @GetMapping("/monthData/{id}")
    public MonthData queryById(@PathVariable Long id) {

        return monthDataService.queryById(id);
    }
}
