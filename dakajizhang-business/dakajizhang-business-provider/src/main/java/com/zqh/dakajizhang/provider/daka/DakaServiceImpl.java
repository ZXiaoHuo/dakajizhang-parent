package com.zqh.dakajizhang.provider.daka;

import com.zqh.dakajizhang.mapper.DakaMapper;
import com.zqh.dakajizhang.mapper.DakaRuleMapper;
import com.zqh.dakajizhang.pojo.Daka;
import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.service.DakaRuleService;
import com.zqh.dakajizhang.service.DakaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 9:22
 */
@Service
@RequiredArgsConstructor
public class DakaServiceImpl implements DakaService {

    private final DakaMapper dakaMapper;

    @Override
    public Daka findById(Daka daka) {
        return dakaMapper.selectByPrimaryKey(daka.getId());
    }

    @Override
    public void doDaka(Daka daka) {
        dakaMapper.updateByPrimaryKeySelective(daka);
    }

    @Override
    public void save(Daka daka) {
        dakaMapper.insert(daka);
    }

    @Override
    public List<Daka> findList(Daka daka) {
        return dakaMapper.findList(daka);
    }
}
