package com.national.raise.web.controller;

import com.national.raise.web.pojo.MonthData;
import com.national.raise.web.service.MonthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class HelloController {

    @RequestMapping("/test2")
    public String hello() {
        return "国奶养育";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @Autowired
    private MonthDataService monthDataService;

    /*** 根据id获取用户 * @param id 用户id * @return 用户 */
    @GetMapping("/monthData/{id}")
    public MonthData queryById(@PathVariable Long id) {

        return monthDataService.queryById(id);
    }

    /**
     * 本地访问内容地址 ：http://localhost:8080/lmycc/hello
     */
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "/index";
    }


    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("testUpload") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "D://test//";
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
