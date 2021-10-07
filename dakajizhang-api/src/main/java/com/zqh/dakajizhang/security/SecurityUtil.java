package com.zqh.dakajizhang.security;

import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 10:32
 */
@Component
public class SecurityUtil {

    public static Map<String,User> users = new HashMap<>();

    /*@Autowired
    private static UserService userService;

    public static User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = userService.findUserByUsername(principal.toString());
        return user;
    }*/

    public static User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        return users.get(principal);
    }
}
