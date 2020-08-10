package com.liuym.fund.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author liuym
 * @date 2020/8/6 16:26
 * @description
 */
@Getter
@Setter
@ToString
public class FundRealTimeDTO {

    private String fundCode; // 基金代码
    private String name; // 基金名称
    private Date jzrq; // 净值日期
    private String dwjz; // 当日净值
    private String gsz; // 估算净值
    private String gszzl; // 估算涨跌百分比 即-0.42%
    private Date gztime; // 估值时间
}
