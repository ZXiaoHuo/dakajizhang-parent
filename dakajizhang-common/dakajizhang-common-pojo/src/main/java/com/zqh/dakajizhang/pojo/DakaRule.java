package com.zqh.dakajizhang.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zhangqh
 * @date 2021/9/7 0007 17:36
 */
@Data
@Entity
@Table(name = "tb_daka_rule")
public class DakaRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}

