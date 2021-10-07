package com.zqh.dakajizhang.business;

import com.zqh.dakajizhang.dto.DakaRuleDTO;
import com.zqh.dakajizhang.dto.RestResult;
import com.zqh.dakajizhang.exception.GlobalException;
import com.zqh.dakajizhang.pojo.Daka;
import com.zqh.dakajizhang.pojo.DakaRule;
import com.zqh.dakajizhang.pojo.DakaRuleItems;
import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.security.SecurityUtil;
import com.zqh.dakajizhang.service.DakaRuleItemsService;
import com.zqh.dakajizhang.service.DakaRuleService;
import com.zqh.dakajizhang.service.DakaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/15 0015 10:32
 */
@Slf4j
@Service
public class DakaBizService {

    @Autowired
    private DakaService dakaService;
    @Autowired
    private DakaRuleService dakaRuleService;
    @Autowired
    private DakaRuleItemsService dakaRuleItemsService;

    @Transactional
    public Boolean enabledGZZ(Long id) {
        try {
            DakaRule dakaRule = new DakaRule();
            dakaRule.setId(id);
            List<DakaRule> list = dakaRuleService.findList(dakaRule);
            if (list.size() <= 0) {
                return false;
            }
            DakaRule dakaRule1 = list.get(0);
            if (dakaRule1.getEnabled()) {
                dakaRule1.setEnabled(false);
                dakaRuleService.enabled(dakaRule1);
                return true;
            }

            List<DakaRule> byTrue = findGzzList(true,null);
            if (byTrue.size() > 0) {
                DakaRule dakaRule2 = byTrue.get(0);
                dakaRule2.setEnabled(false);
                dakaRuleService.enabled(dakaRule2);
            }

            dakaRule1.setEnabled(true);
            dakaRuleService.enabled(dakaRule1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public void saveGZZ(DakaRuleDTO dakaRuleDTO) {
        DakaRule dakaRule = new DakaRule();
        dakaRule.setName(dakaRuleDTO.getName());
        dakaRule.setEnabled(false);
        dakaRule.setCreateTime(LocalDateTime.now());
        Long userid = SecurityUtil.getCurrentUser().getId();
        dakaRule.setUserId(userid);
        Long id = dakaRuleService.save(dakaRule);
        List<DakaRuleItems> list = dakaRuleDTO.getList();
        for (DakaRuleItems dakaRuleItems : list) {
            dakaRuleItems.setDakaRuleId(id);
        }
        saveGZX(list);
    }

    @Transactional
    public Boolean saveGZX(List<DakaRuleItems> ruleItems) {
        try {
            for (DakaRuleItems ruleItem : ruleItems) {
                ruleItem.setCreateTime(LocalDateTime.now());
                ruleItem.setEnabled(true);
                dakaRuleItemsService.save(ruleItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public List<DakaRuleItems> findGzxList(Long id) {
        return dakaRuleItemsService.findByGzzId(id);
    }


    public List<Daka> findDakaList(String createTime) {
        Long uid = SecurityUtil.getCurrentUser().getId();
        Daka daka = new Daka();
        daka.setUserId(uid);
        daka.setCreateTime(null);
        if (StringUtils.isNotBlank(createTime)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(createTime);
                Instant instant = date.toInstant();
                LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                daka.setCreateTime(localDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }

        return dakaService.findList(daka);
    }

    @Transactional
    public void daka(Long id) {
        Daka query = new Daka();
        query.setId(id);
        Daka daka = dakaService.findById(query);
        LocalTime startTime = daka.getStartTime();
        LocalTime endTime = daka.getEndTime();
        LocalTime now = LocalTime.now();
        if (now.isBefore(startTime)||now.isAfter(endTime)) {
            //return RestResult.error("不在打卡时间范围，打卡失败");
            throw new GlobalException("不在打卡时间范围，打卡失败");
        }
        if (daka.getIsDaka()) {
            //return RestResult.error("请勿重复打卡");
            throw new GlobalException("请勿重复打卡");
        }
        daka.setIsDaka(true);
        daka.setUpdateTime(LocalDateTime.now());
        dakaService.doDaka(daka);
    }


    public List<DakaRule> findGzzList(Boolean enabled,Long id) {
        User currentUser = SecurityUtil.getCurrentUser();
        DakaRule dakaRule = new DakaRule();
        if (enabled != null) {
            dakaRule.setEnabled(enabled);
        }
        if (id != null) {
            dakaRule.setId(id);
        }
        dakaRule.setUserId(currentUser.getId());
        return dakaRuleService.findList(dakaRule);
    }
}
