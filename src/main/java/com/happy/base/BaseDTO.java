package com.happy.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {
    //    @NotEmpty(message = "请求唯一流水号不能为空")
    private String reqNo;
    //    @NotEmpty(message = "请求时间戳不能为空")
    private String timestamp;
    //    @NotEmpty(message = "appId不能为空")
    private String appId;
    //    @NotEmpty(message = "请求业务参数不能为空")
    private String bizData;
    private Long pageNo = 0L;
    private Long pageSize = 10L;
    private Long pageTotal = 0L;
}
