package com.happy.base;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReqDTO extends BaseDTO {
    private String sign;
    private String signType= Constants.MD5;

}
