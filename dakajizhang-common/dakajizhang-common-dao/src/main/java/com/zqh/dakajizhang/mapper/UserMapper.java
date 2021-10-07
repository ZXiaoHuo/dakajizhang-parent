package com.zqh.dakajizhang.mapper;

import com.zqh.dakajizhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhangqh
 * @date 2021/8/8 0008 20:53
 */
/*@Mapper*/
@Repository
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{

    User findByUsername(@Param("username")String username);
}
