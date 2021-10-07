package com.zqh.dakajizhang.provider.dakarule;

import com.zqh.dakajizhang.mapper.DakaRuleMapper;
import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.service.DakaRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 9:22
 */
@Service
@RequiredArgsConstructor
public class DakaRuleServiceImpl implements DakaRuleService {

    private final DakaRuleMapper dakaRuleMapper;

    @Override
    public List<DakaRule> findList(DakaRule dakaRule) {
        //return dakaRuleMapper.findList(dakaRule);
        return dakaRuleMapper.select(dakaRule);
    }

    @Override
    public void enabled(DakaRule dakaRule) {
        dakaRuleMapper.updateByPrimaryKey(dakaRule);
    }

    @Override
    public Long save(DakaRule dakaRule) {
        dakaRuleMapper.insert(dakaRule);
        return dakaRule.getId();
    }
}
