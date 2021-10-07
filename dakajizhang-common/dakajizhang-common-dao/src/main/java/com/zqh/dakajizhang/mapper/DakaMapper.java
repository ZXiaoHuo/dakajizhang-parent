package com.zqh.dakajizhang.mapper;

import com.zqh.dakajizhang.pojo.Daka;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 17:07
 */
public interface DakaMapper extends tk.mybatis.mapper.common.Mapper<Daka>{
    List<Daka> findList(Daka daka);
}
