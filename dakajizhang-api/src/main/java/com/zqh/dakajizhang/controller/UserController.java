package com.zqh.dakajizhang.controller;

import com.zqh.dakajizhang.business.UserBizService;
import com.zqh.dakajizhang.dto.RestResult;
import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.security.SecurityUtil;
import com.zqh.dakajizhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author zhangqh
 * @date 2021/9/14 0014 10:05
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserBizService userBizService;

    @GetMapping("currentuser")
    public RestResult getCurrentUser() {
        User currentUser = userBizService.getCurrentUser();
        return RestResult.success(currentUser);
    }

    @PostMapping("save")
    public RestResult save(@RequestBody User user) {
        if (user.getUsername()==null)return RestResult.error("用户名不能为空");
        if (user.getPassword()==null)return RestResult.error("密码不能为空");
        if (userService.findUserByUsername(user.getUsername())!=null) return RestResult.error("用户名不能重复");
        if (userBizService.sava(user)) return RestResult.success("注册成功");
        return RestResult.error("注册失败");
    }
}
