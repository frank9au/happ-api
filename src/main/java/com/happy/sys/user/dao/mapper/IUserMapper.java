package com.happy.sys.user.dao.mapper;

import com.happy.sys.user.dao.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lenovo
 * @since 2023-03-10
 */
public interface IUserMapper extends BaseMapper<UserPO> {
    List<UserPO> selectListByPage(@Param("page") IPage<UserPO> page, @Param("dto") UserPO userPO);
}
