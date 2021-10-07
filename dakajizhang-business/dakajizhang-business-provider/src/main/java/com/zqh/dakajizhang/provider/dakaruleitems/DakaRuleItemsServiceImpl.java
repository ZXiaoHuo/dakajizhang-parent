package com.zqh.dakajizhang.provider.dakaruleitems;

import com.zqh.dakajizhang.mapper.DakaRuleItemsMapper;
import com.zqh.dakajizhang.pojo.DakaRuleItems;
import com.zqh.dakajizhang.service.DakaRuleItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 16:10
 */
@Service
@RequiredArgsConstructor
public class DakaRuleItemsServiceImpl implements DakaRuleItemsService {

    private final DakaRuleItemsMapper dakaRuleItemsMapper;

    @Override
    public void save(DakaRuleItems dakaRuleItems) {
        dakaRuleItemsMapper.insert(dakaRuleItems);
    }

    @Override
    public List<DakaRuleItems> findByUid(Long uid) {
        return dakaRuleItemsMapper.findByUid(uid);
    }

    @Override
    public List<DakaRuleItems> findByGzzId(Long id) {
        return dakaRuleItemsMapper.findByGzzId(id);
    }
}
