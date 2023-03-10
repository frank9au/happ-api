package com.happy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum StatusEnums {

    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    DELETED(3, "删除"),
    ;
    private Integer code;
    private String value;
}
