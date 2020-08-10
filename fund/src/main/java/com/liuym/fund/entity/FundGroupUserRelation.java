package com.liuym.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author liuym
 * @date 2020/8/10 9:59
 * @description
 */
@Data
public class FundGroupUserRelation {

    @TableId(type = IdType.AUTO)
    private Integer relationId;
    private Integer groupId;
    private Integer userId;
}
