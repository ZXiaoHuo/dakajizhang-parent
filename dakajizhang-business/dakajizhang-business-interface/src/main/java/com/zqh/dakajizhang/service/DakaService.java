package com.zqh.dakajizhang.service;

import com.zqh.dakajizhang.pojo.Daka;
import com.zqh.dakajizhang.pojo.DakaRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 9:19
 */
@Component
@FeignClient(name = "dakajizhang-business-provider",path = "/business/daka")
public interface DakaService {

    @PostMapping("findById")
    Daka findById(@RequestBody Daka daka);

    @PostMapping("do")
    void doDaka(@RequestBody Daka daka);

    @PostMapping("save")
    void save(@RequestBody Daka daka);

    @PostMapping("list")
    List<Daka> findList(@RequestBody Daka daka);


}

