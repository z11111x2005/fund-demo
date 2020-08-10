package com.liuym.fund.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * created by noob
 * 2020/8/9 20:47
 */
@Data
public class UserFormDTO {

    @NotBlank(message = "用户名不能为空")
    private String userName;

}
