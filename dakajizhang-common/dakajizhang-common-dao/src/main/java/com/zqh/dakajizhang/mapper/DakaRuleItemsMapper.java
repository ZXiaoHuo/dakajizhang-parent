package com.zqh.dakajizhang.mapper;

import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.pojo.DakaRuleItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 16:06
 */
public interface DakaRuleItemsMapper extends tk.mybatis.mapper.common.Mapper<DakaRuleItems>{

    List<DakaRuleItems> findByUid(@Param("uid") Long uid);
    List<DakaRuleItems> findByGzzId(@Param("id") Long id);
}
