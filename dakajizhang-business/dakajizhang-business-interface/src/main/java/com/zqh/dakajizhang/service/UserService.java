package com.zqh.dakajizhang.service;

import com.zqh.dakajizhang.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/8/8 0008 20:58
 */
@Component
@FeignClient(name = "dakajizhang-business-provider",path = "/business/user")
public interface UserService {

    @GetMapping("list")
    List<User> findList();

    @GetMapping("{id}")
    User findUserById(@PathVariable("id") Long id);

    @GetMapping("byUsername")
    User findUserByUsername(@RequestParam String username);

    @PostMapping("save")
    void save(@RequestBody User user);
}
