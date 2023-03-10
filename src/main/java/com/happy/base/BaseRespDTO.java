package com.happy.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRespDTO extends BaseDTO {
    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
}
