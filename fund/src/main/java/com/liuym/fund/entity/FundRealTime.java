package com.liuym.fund.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author liuym
 * @date 2020/8/6 17:13
 * @description
 */
@Data
public class FundRealTime {

    @TableId
    private String fundCode;

    private String fundName;

    private Date jzrq;

    private String dwjz;

    private String gsz;

    private String gszzl;

    private Date gztime;

    private Date createTime;

    private String type;
}
