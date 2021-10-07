package com.zqh.dakajizhang.provider.daka;

import com.zqh.dakajizhang.pojo.Daka;
import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.service.DakaRuleService;
import com.zqh.dakajizhang.service.DakaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 9:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/business/daka")
public class DakaProviderController {

    private final DakaService dakaService;

    @PostMapping("findById")
    Daka findById(@RequestBody Daka daka) {
        return dakaService.findById(daka);
    }

    @PostMapping("do")
    void doDaka(@RequestBody Daka daka) {
        dakaService.doDaka(daka);
    }

    @PostMapping("save")
    void save(@RequestBody Daka daka) {
        dakaService.save(daka);
    }

    @PostMapping("list")
    List<Daka> findList(@RequestBody Daka daka) {
        return dakaService.findList(daka);
    }

}
