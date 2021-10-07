package com.zqh.dakajizhang.provider.dakarule;

import com.zqh.dakajizhang.pojo.DakaRule;
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
@RequestMapping("/business/dakarule")
public class DakaRuleProviderController {

    private final DakaRuleService dakaRuleService;

    @PostMapping("list")
    List<DakaRule> findList(@RequestBody DakaRule dakaRule){
        return dakaRuleService.findList(dakaRule);
    }

    @PostMapping("enabled")
    void enabled(@RequestBody DakaRule dakaRule) {
        dakaRuleService.enabled(dakaRule);
    }

    @PostMapping("save")
    Long save(@RequestBody DakaRule dakaRule) {
        return dakaRuleService.save(dakaRule);
    }
}
