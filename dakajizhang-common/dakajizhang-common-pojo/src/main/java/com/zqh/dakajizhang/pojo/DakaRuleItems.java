package com.zqh.dakajizhang.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author zhangqh
 * @date 2021/9/7 0007 17:37
 */
@Data
@Entity
@Table(name = "tb_daka_rule_items")
public class DakaRuleItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "daka_rule_id")
    private Long dakaRuleId;

    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
