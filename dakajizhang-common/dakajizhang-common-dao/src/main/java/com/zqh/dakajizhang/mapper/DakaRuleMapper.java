package com.zqh.dakajizhang.mapper;

import com.zqh.dakajizhang.pojo.DakaRule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 9:04
 */
@Repository
public interface DakaRuleMapper extends tk.mybatis.mapper.common.Mapper<DakaRule>{

    List<DakaRule> findList(DakaRule dakaRule);
}
