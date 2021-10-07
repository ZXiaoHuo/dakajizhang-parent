package com.zqh.dakajizhang.provider.user;

import com.zqh.dakajizhang.mapper.UserMapper;
import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/8/8 0008 21:07
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public List<User> findList() {
        return userMapper.selectAll();
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userMapper.insertSelective(user);
    }
}
