package com.zqh.dakajizhang.dto;

import com.zqh.dakajizhang.pojo.DakaRuleItems;
import lombok.Data;

import java.util.List;

/**
 * @author zhangqh
 * @date 2021/10/5 0005 22:21
 */
@Data
public class DakaRuleDTO {
    private String name;
    private List<DakaRuleItems> list;
}
