package com.happy.base;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReqDTO implements Serializable {
    @NotEmpty(message = "请求唯一流水号不能为空")
    private String reqNo;
    @NotEmpty(message = "请求时间戳不能为空")
    private String timestamp;
    @NotEmpty(message = "appId不能为空")
    private String appId;
    @NotEmpty(message = "请求业务参数不能为空")
    private String bizData;
    @NotEmpty(message = "请求字符串签名不能为空")
    private String sign;
    private String signType= Constants.MD5;
}
