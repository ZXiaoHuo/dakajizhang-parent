package com.zqh.dakajizhang.provider.dakaruleitems;

import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.pojo.DakaRuleItems;
import com.zqh.dakajizhang.service.DakaRuleItemsService;
import com.zqh.dakajizhang.service.DakaRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 9:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/business/dakaruleitems")
public class DakaRuleItemsProviderController {

    private final DakaRuleItemsService dakaRuleItemsService;

    @PostMapping("save")
    void save(@RequestBody DakaRuleItems dakaRuleItems) {
        dakaRuleItemsService.save(dakaRuleItems);
    }

    @PostMapping("findByUid")
    List<DakaRuleItems> findByUid(@RequestParam Long uid) {
        return dakaRuleItemsService.findByUid(uid);
    }

    @PostMapping("findByGzzId")
    List<DakaRuleItems> findByGzzId(@RequestParam Long id) {
        return dakaRuleItemsService.findByGzzId(id);
    }
}
