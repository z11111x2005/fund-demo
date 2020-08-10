package com.liuym.fund.dto;

import lombok.Data;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 16:28
 * @description
 */
@Data
public class FundGroupRelationFormDTO {

    private Integer groupId;
    private List<String> fundCodes;
}
