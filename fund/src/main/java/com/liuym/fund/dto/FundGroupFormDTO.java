package com.liuym.fund.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author liuym
 * @date 2020/8/10 12:52
 * @description
 */
@Data
public class FundGroupFormDTO {

    @NotBlank(message = "分组名称不能为空")
    private String groupName;

    @NotNull(message = "用户不能为空")
    private Integer userId;
}
