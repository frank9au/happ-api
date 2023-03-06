package com.happy.util;

import com.happy.base.BaseRespDTO;
import com.happy.enums.ReturnEnums;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;

public class ResultUtils {

    public static BaseRespDTO success(){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_SUCCESS.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_SUCCESS.getMessage());
        baseRespDTO.setSubCode(ReturnEnums.SUB_SUCCESS.getCode());
        baseRespDTO.setSubMsg(ReturnEnums.SUB_SUCCESS.getMessage());
        return baseRespDTO;
    }

    public static BaseRespDTO fail(){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_FAIL501.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_FAIL501.getMessage());
        return baseRespDTO;
    }

    public static BaseRespDTO bizSuccess(String bizData){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_SUCCESS.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_SUCCESS.getMessage());
        baseRespDTO.setSubCode(ReturnEnums.SUB_SUCCESS.getCode());
        baseRespDTO.setSubMsg(ReturnEnums.SUB_SUCCESS.getMessage());
        baseRespDTO.setBizData(StringUtils.hasText(bizData)?bizData:"");
        return baseRespDTO;
    }

    public static BaseRespDTO bizFail(){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_SUCCESS.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_SUCCESS.getMessage());
        baseRespDTO.setSubCode(ReturnEnums.SUB_FAIL600.getCode());
        baseRespDTO.setSubMsg(ReturnEnums.SUB_FAIL600.getMessage());
        return baseRespDTO;
    }

    public static BaseRespDTO bizFail(ReturnEnums subReturnEnum){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_SUCCESS.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_SUCCESS.getMessage());
        baseRespDTO.setSubCode(subReturnEnum.getCode());
        baseRespDTO.setSubMsg(subReturnEnum.getMessage());
        return baseRespDTO;
    }

    public static BaseRespDTO bizFail(String subCode, String subMsg){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_SUCCESS.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_SUCCESS.getMessage());
        baseRespDTO.setSubCode(subCode);
        baseRespDTO.setSubMsg(subMsg);
        return baseRespDTO;
    }

    public static BaseRespDTO bizFail(Exception e, ReturnEnums subReturnEnum){
        BaseRespDTO baseRespDTO = new BaseRespDTO();
        baseRespDTO.setCode(ReturnEnums.OP_SUCCESS.getCode());
        baseRespDTO.setMsg(ReturnEnums.OP_SUCCESS.getMessage());
        if(e instanceof ValidationException){
            baseRespDTO.setSubCode(ReturnEnums.SUB_FAIL610.getCode());
            baseRespDTO.setSubMsg(e.getMessage());
            return baseRespDTO;
        }
        baseRespDTO.setSubCode(subReturnEnum.getCode());
        baseRespDTO.setSubMsg(subReturnEnum.getMessage());
        return baseRespDTO;
    }
}
