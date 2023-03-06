package com.happy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ReturnEnums {

    //系统返回码
    OP_SUCCESS("000", "成功", "成功"),
    OP_FAIL501("501", "系统不可用，稍后重试", "系统不可用，稍后重试"),
    OP_FAIL502("502", "系统繁忙，稍后重试", "系统繁忙，稍后重试"),
    OP_FAIL503("503", "json解析报错", "json解析报错"),
    OP_FAIL504("504", "枚举解析失败", "枚举解析失败"),
    OP_FAIL505("505", "接口参数无效", "参数无效，请检查参数，格式不对、非法值、越界等"),
    OP_FAIL506("506", "配置有误", "系统配置有误或不全"),

    //公共业务组
    SUB_SUCCESS("000000", "成功", "成功"),
    SUB_FAIL600("600", "业务处理异常", "业务处理时，出现未知错误"),
    SUB_FAIL610("610", "接口参数无效", "参数无效，请检查参数，格式不对、非法值、越界等"),
    SUB_FAIL620("620", "签名校验不通过", "请检查签名参数以及签名算法是否正确"),

    //自定义异常码
    ;
    /**
     * 状态码
     */
    private String code;
    /**
     * 提示语（给用户看的）
     */
    private String message;
    /**
     * 状态详细描述(主要日志中给研发或者运维使用)
     */
    private String exception;
}
