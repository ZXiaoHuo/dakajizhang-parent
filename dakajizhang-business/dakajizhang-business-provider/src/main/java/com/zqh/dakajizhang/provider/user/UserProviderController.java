package com.zqh.dakajizhang.provider.user;

import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/8/8 0008 21:11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/business/user")
public class UserProviderController {

    private final UserService userService;

    @GetMapping("list")
    List<User> findList(){
        return userService.findList();
    }

    @GetMapping("{id}")
    User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("byUsername")
    User findUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping("save")
    void save(@RequestBody User user) {
        userService.save(user);
    }
}
