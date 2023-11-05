package com.hl.controller;


import com.hl.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * 模拟接口
 *
 * @version 1.0
 * @author: HL
 * @date: 2023/10/14
 */

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/{name}")
    public String getNameByGet(@PathVariable(value = "name") String name) {
        return "发送GET请求 你的名字是：" + name;
    }

    @PostMapping()
    public String getNameByPost(@RequestParam(value = "name") String name) {
        return "发送POST请求 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getNameByPostWithJson(@RequestBody User user) {
        return "发送POST请求 JSON中你的名字是：" + user.getUsername();
    }

}