package com.liuym.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author liuym
 * @date 2020/8/10 9:25
 * @description
 */
@Data
public class FundGroup {

    @TableId(type = IdType.AUTO)
    private Integer groupId;

    private String groupName;

    private Integer groupType;
}
