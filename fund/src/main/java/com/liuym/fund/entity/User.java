package com.liuym.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author liuym
 * @date 2020/8/6 17:13
 * @description
 */
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;
}
