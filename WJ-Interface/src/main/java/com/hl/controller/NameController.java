package com.hl.controller;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hl.model.InvokeInterfaceRequest;
import com.hl.model.User;
import com.hl.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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

    @PostMapping("/test")
    public String test(@RequestParam String name) {
        return "test" + name;
    }

    @PostMapping("/user")
    public String getNameByPostWithJson(@RequestBody InvokeInterfaceRequest invokeInterfaceRequest, HttpServletRequest request) throws UnsupportedEncodingException {
        String accessKey = request.getHeader("accessKey");
        // 防止中文乱码
        String body = URLDecoder.decode(request.getHeader("body"), StandardCharsets.UTF_8.name());

        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判断是否有空
        if (hasBlank) {
            return "无权限";
        }
       String userInfo =  invokeInterfaceRequest.getRequestParams();
        //String转对象
        User user =  JSONUtil.toBean(userInfo, User.class);
        String result =  "发送POST请求 JSON中你的名字是：" + user.getUsername();
        return result;
    }

}
