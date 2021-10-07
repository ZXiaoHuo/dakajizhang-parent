package com.zqh.dakajizhang.business;

import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.security.SecurityUtil;
import com.zqh.dakajizhang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * @author zhangqh
 * @date 2021/9/15 0015 10:22
 */
@Slf4j
@Service
public class UserBizService {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        Long id = SecurityUtil.getCurrentUser().getId();
        return userService.findUserById(id);
    }

    @Transactional
    public Boolean sava(User user) {
        try {
            String encode = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encode);
            String nickname = "用户_" + UUID.randomUUID().toString().substring(0,7);
            user.setNickname(nickname);
            String uuid = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 15);
            user.setUuid(uuid);
            user.setAvatar("https://img2.baidu.com/it/u=2108319215,1494231136&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=690");
            user.setCreateTime(LocalDateTime.now());
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
