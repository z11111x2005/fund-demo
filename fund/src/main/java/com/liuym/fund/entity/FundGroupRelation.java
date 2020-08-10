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
public class FundGroupRelation {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer groupId;

    private String fundCode;
}
