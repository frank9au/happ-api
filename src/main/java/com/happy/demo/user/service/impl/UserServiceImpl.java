package com.happy.demo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.happy.demo.user.dao.mapper.IUserMapper;
import com.happy.demo.user.dao.po.UserPO;
import com.happy.demo.user.service.IUserService;
import com.happy.enums.StatusEnums;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lenovo
 * @since 2023-03-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<IUserMapper, UserPO> implements IUserService {
    @Resource
    private IUserMapper userMapper;

    @Override
    public UserPO saveOrModify(UserPO userPO) {
        this.saveOrUpdate(userPO);
        return userPO;
    }

    @Override
    public UserPO detail(Long id) {
        return this.getById(id);
    }

    @Override
    public Boolean delete(Long id) {
        UserPO userPO = this.getById(id);
        if(Objects.isNull(userPO)) {
            return false;
        }
        if(StatusEnums.DELETED.getCode() == userPO.getStatus()){
            return true;
        }
        UpdateWrapper<UserPO> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(UserPO::getStatus, StatusEnums.DELETED.getCode())
                    .eq(UserPO::getId, id);
        int update = userMapper.update(null, wrapper);
        if(update > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<UserPO> selectListByPage(IPage<UserPO> page, UserPO userPO) {
        List<UserPO> userPOList = userMapper.selectListByPage(page, userPO);
        return userPOList;
    }
}
