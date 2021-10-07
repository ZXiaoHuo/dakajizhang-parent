package com.zqh.dakajizhang.security;

import com.alibaba.fastjson.JSON;
import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bxsheng
 * @blogAddress www.kdream.cn
 * @createTIme 2020/9/16
 * since JDK 1.8
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        //如果请求头中没有Authorization信息则直接放行了
        if(tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        //如果请求头中有token,则进行解析，并且设置认证信息
        if(!JwtTokenUtils.isExpiration(tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX,""))){
            //设置上下文
            Authentication authentication = getAuthentication(tokenHeader);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        super.doFilterInternal(request, response, chain);
    }

    //获取用户信息
    private Authentication getAuthentication(String tokenHeader){
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUserName(token);
        // 获得权限 添加到权限上去
        String role = JwtTokenUtils.getUserRole(token);
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });
        if(username != null){
            setCurrentUser(username);
            return new UsernamePasswordAuthenticationToken(username, null,roles);
        }
        return null;
    }


    public void setCurrentUser(String username) {
        User user = userService.findUserByUsername(username);
        SecurityUtil.users.put(username,user);
    }

}

