package com.liuym.fund.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liuym
 * @date 2020/8/6 14:58
 * @description
 */
@Getter
@Setter
@ToString
public class FundInfoDTO {

    private String code;
    private String fundPY;
    private String fundName;
    private String type;
    private String fundPinYin;

}
