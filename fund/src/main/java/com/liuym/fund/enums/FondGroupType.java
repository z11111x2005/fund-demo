package com.liuym.fund.enums;

import lombok.Getter;

/**
 * @author liuym
 * @date 2020/8/10 17:42
 * @description
 */
@Getter
public enum FondGroupType {

    EXCLUSIVE(1, "独享"),
    SHARED(2, "共享"),
    ;

    private Integer value;
    private String label;

    FondGroupType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
