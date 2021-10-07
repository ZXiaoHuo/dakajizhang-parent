package com.zqh.dakajizhang.service;

import com.zqh.dakajizhang.pojo.DakaRuleItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 16:09
 */
@Component
@FeignClient(name = "dakajizhang-business-provider",path = "/business/dakaruleitems")
public interface DakaRuleItemsService {

    @PostMapping("save")
    void save(@RequestBody DakaRuleItems dakaRuleItems);

    @PostMapping("findByUid")
    List<DakaRuleItems> findByUid(@RequestParam Long uid);

    @PostMapping("findByGzzId")
    List<DakaRuleItems> findByGzzId(@RequestParam Long id);
}
