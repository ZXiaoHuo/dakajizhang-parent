package com.zqh.dakajizhang.schedule;

import com.zqh.dakajizhang.pojo.Daka;
import com.zqh.dakajizhang.pojo.DakaRuleItems;
import com.zqh.dakajizhang.pojo.User;
import com.zqh.dakajizhang.service.DakaRuleItemsService;
import com.zqh.dakajizhang.service.DakaService;
import com.zqh.dakajizhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhangqh
 * @date 2021/9/8 0008 16:31
 */
@Component
@Configuration
@EnableScheduling
public class DakaTaskGenerate {

    @Autowired
    private UserService userService;
    @Autowired
    private DakaRuleItemsService dakaRuleItemsService;
    @Autowired
    private DakaService dakaService;

    //3.添加定时任务
    //@Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")//每天0点
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        generateDakaTask();
    }




    private void generateDakaTask() {
        //每个用户(全部)
        List<User> users = userService.findList();
        for (User user : users) {
            List<DakaRuleItems> items = dakaRuleItemsService.findByUid(user.getId());
            if (items.size()<=0) continue;
            for (DakaRuleItems item : items) {
                //根据规则项生成实例
                Daka daka = new Daka();
                daka.setIsDaka(false);
                daka.setCreateTime(LocalDateTime.now());
                daka.setName(item.getName());
                daka.setUserId(user.getId());
                daka.setStartTime(item.getStartTime());
                daka.setEndTime(item.getEndTime());
                dakaService.save(daka);
                System.err.println(LocalDateTime.now()+" 用户《"+user.getUsername()+"》规则项生成");
            }
        }

    }

}
