package com.hl.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hl.project.common.ErrorCode;
import com.hl.project.exception.BusinessException;
import com.hl.project.mapper.UserMapper;
import com.hl.project.model.entity.User;
import com.hl.project.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author: HL
 * @date: 2023/2/6
 */

@Service(version = "${dubbo.service.version}")
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccessKey, accessKey);
        return userMapper.selectOne(lambdaQueryWrapper);
    }
}