package com.hl.project.service;

import com.hl.project.model.entity.InterfaceInfo;

public interface InnerInterfaceInfoService {

    /**
     * 根据path、method查询接口信息
     *
     * @param path   请求路径
     * @param method 请求方法
     * @return InterfaceInfo
     */
    InterfaceInfo getInvokeInterfaceInfo(String path, String method);

    InterfaceInfo getInvokeInterfaceInfoById(Long id, String method);
}



