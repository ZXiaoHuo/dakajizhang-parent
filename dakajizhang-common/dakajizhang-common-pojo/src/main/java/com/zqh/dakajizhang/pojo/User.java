package com.zqh.dakajizhang.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zhangqh
 * @date 2021/8/8 0008 20:25
 */
@Data
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "salt")
    private String salt;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
