package com.hl.project.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hl.project.common.ErrorCode;
import com.hl.project.exception.BusinessException;
import com.hl.project.mapper.InterfaceInfoMapper;
import com.hl.project.model.entity.InterfaceInfo;
import com.hl.project.service.InnerInterfaceInfoService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service(version = "${dubbo.service.version}")
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInvokeInterfaceInfo(String url, String method) {
        if (StrUtil.hasBlank(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InterfaceInfo::getUrl, url).eq(InterfaceInfo::getMethod, method);
        return interfaceInfoMapper.selectOne(lambdaQueryWrapper);
    }

}
