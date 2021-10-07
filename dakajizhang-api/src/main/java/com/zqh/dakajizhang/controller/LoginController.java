package com.zqh.dakajizhang.controller;

import com.alibaba.fastjson.JSON;
import com.zqh.dakajizhang.dto.RestResult;
import com.zqh.dakajizhang.security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqh
 * @date 2021/8/31 0031 15:50
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public RestResult login(@RequestParam String username,@RequestParam String password) {
        // 登陆验证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //String s = JSON.toJSONString(authentication);
        //创建jwt信息
        //String token = JwtTokenUtils.createToken(s,null, true);
        String token = JwtTokenUtils.createToken(username,null, true);
        return RestResult.success(token);
    }
}
