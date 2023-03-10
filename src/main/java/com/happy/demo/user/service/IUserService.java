package com.happy.demo.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.demo.user.dao.po.UserPO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lenovo
 * @since 2023-03-10
 */
public interface IUserService extends IService<UserPO> {
    UserPO saveOrModify(UserPO userPO);
    UserPO detail(Long id);
    Boolean delete(Long id);
    List<UserPO> selectListByPage(IPage<UserPO> page, UserPO userPO);
}
