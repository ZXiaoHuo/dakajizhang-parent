package com.zqh.dakajizhang.controller;

import com.zqh.dakajizhang.business.DakaBizService;
import com.zqh.dakajizhang.dto.DakaRuleDTO;
import com.zqh.dakajizhang.dto.RestResult;
import com.zqh.dakajizhang.pojo.Daka;
import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.pojo.DakaRuleItems;
import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.security.SecurityUtil;
import com.zqh.dakajizhang.security.UserDetailsImpl;
import com.zqh.dakajizhang.service.DakaRuleItemsService;
import com.zqh.dakajizhang.service.DakaRuleService;
import com.zqh.dakajizhang.service.DakaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/7 0007 17:28
 */
@RestController
@RequestMapping("api/daka")
public class DakaController {

    @Autowired
    private DakaService dakaService;
    @Autowired
    private DakaRuleService dakaRuleService;
    @Autowired
    private DakaRuleItemsService dakaRuleItemsService;
    @Autowired
    private DakaBizService dakaBizService;

    /**
     * 规则组列表
     * @return
     */
    @GetMapping("gzz/list")
    public RestResult gzzList() {
        return RestResult.success(dakaBizService.findGzzList(null,null));
    }

    /**
     * 启禁用规则组
     * @param id
     * @return
     */
    @PostMapping("gzz/{id}/enabled")
    @Transactional
    public RestResult enabledGzz(@PathVariable("id") Long id) {
       if (dakaBizService.enabledGZZ(id)) return RestResult.success();
       return RestResult.error();
    }

    /**
     * 保存规则组
     * @param dakaRuleDTO
     * @return
     */
    @PostMapping("gzz/save")
    public RestResult saveGzz(@RequestBody DakaRuleDTO dakaRuleDTO) {
        dakaBizService.saveGZZ(dakaRuleDTO);
        return RestResult.success();
    }

    /**
     * 保存规则项
     * @param ruleItems
     * @return
     */
    @PostMapping("gzx/save")
    public RestResult saveGzx(@RequestBody List<DakaRuleItems> ruleItems) {
        if (dakaBizService.saveGZX(ruleItems)) return RestResult.success();
        return RestResult.error();
    }

    /**
     * 根据规则组id获取规则组的规则项列表
     * @param id
     * @return
     */
    @GetMapping("gzx/{id}/list")
    public RestResult getGzxByGzzId(@PathVariable("id") Long id){
        List<DakaRuleItems> gzxList = dakaBizService.findGzxList(id);
        return RestResult.success(gzxList);
    }

    /**
     * 每日打卡列表
     * @param createTime
     * @return
     */
    @GetMapping("list")
    public RestResult dakaList(String createTime) {
        List<Daka> list = dakaBizService.findDakaList(createTime);
        return RestResult.success(list);
    }

    /**
     * 打卡操作
     * @param id
     * @return
     */
    @PostMapping("{id}/do")
    public RestResult daka(@PathVariable("id") Long id) {
        dakaBizService.daka(id);
        return RestResult.success();
    }



}
