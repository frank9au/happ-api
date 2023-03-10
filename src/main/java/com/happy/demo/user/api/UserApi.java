package com.happy.demo.user.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.happy.base.BaseReqDTO;
import com.happy.base.BaseRespDTO;
import com.happy.demo.user.dao.po.UserPO;
import com.happy.demo.user.service.IUserService;
import com.happy.enums.ReturnEnums;
import com.happy.util.JacksonUtils;
import com.happy.util.ResultUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lenovo
 * @since 2023-03-10
 */
@RestController
@RequestMapping("/user")
public class UserApi {
    @Resource
    private JacksonUtils jacksonUtils;
    @Resource
    private IUserService userService;

    @PostMapping("/save")
    public BaseRespDTO save(@RequestBody BaseReqDTO reqDTO){
        UserPO userPO = jacksonUtils.fromJson2(reqDTO.getBizData(), UserPO.class);
        userPO = userService.saveOrModify(userPO);
        return ResultUtils.bizSuccess(reqDTO, jacksonUtils.toJson2(userPO));
    }

    @GetMapping("/listPage")
    public BaseRespDTO selectListByPage(@RequestBody BaseReqDTO reqDTO){
        IPage<UserPO> page = new Page<>(reqDTO.getPageNo(), reqDTO.getPageSize());
        UserPO userPO = jacksonUtils.fromJson2(reqDTO.getBizData(), UserPO.class);
        List<UserPO> userPOList = userService.selectListByPage(page, userPO);
        return ResultUtils.bizSuccess(reqDTO, jacksonUtils.toJson2(userPOList));
    }

    @GetMapping("/detail")
    public BaseRespDTO detail(@RequestBody BaseReqDTO reqDTO){
        UserPO userPO = jacksonUtils.fromJson2(reqDTO.getBizData(), UserPO.class);
        userPO = userService.detail(userPO.getId());
        return ResultUtils.bizSuccess(reqDTO, jacksonUtils.toJson2(userPO));
    }

    @PostMapping("/delete")
    public BaseRespDTO delete(@RequestBody BaseReqDTO reqDTO){
        UserPO userPO = jacksonUtils.fromJson2(reqDTO.getBizData(), UserPO.class);
        if(userService.delete(userPO.getId())){
            ResultUtils.success();
        }
        return ResultUtils.bizFail(reqDTO, ReturnEnums.SUB_FAIL630);
    }
}

