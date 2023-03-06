package com.happy.exception;

import com.happy.enums.ReturnEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BizException extends RuntimeException {
    private String code;
    private String exception;

    private String subCode;
    private String subMessage;

    public BizException(String message) {
        super(message);
    }

    public BizException(String code, String message,String subCode, String subMessage) {
        super(String.format("%s(%s)", message, code));
        this.code = code;
        this.subCode = subCode;
        this.subMessage = subMessage;
    }

    public BizException(String code, String message, String exception,String subCode, String subMessage) {
        super(String.format("%s(%s)", message, code));
        this.code = code;
        this.exception = exception;
        this.subCode = subCode;
        this.subMessage = subMessage;
    }

    public BizException(ReturnEnums stateEnum, ReturnEnums subStateEnum) {
        this(String.format("%s(%s)", stateEnum.getMessage(), stateEnum.getCode()));
        this.setCode(stateEnum.getCode());
        this.setException(stateEnum.getException());
        this.setSubCode(subStateEnum.getCode());
        this.setSubMessage(subStateEnum.getMessage());
    }

    public BizException(ReturnEnums stateEnum, Exception e, ReturnEnums subStateEnum) {
        this(String.format("%s(%s)", stateEnum.getMessage(), stateEnum.getCode()));
        this.setCode(stateEnum.getCode());
        if (null == e) {
            this.setException(stateEnum.getException());
        } else {
            this.setException(stateEnum.getException() + ":" + e.getMessage());
        }
        this.setSubCode(subStateEnum.getCode());
        this.setSubMessage(subStateEnum.getMessage());
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code='" + code + '\'' +
                ", exception='" + exception + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMessage='" + subMessage + '\'' +
                '}';
    }
}
