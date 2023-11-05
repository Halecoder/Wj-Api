package com.hl.project.service;

import com.hl.project.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author HL
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-11-05 20:27:25
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    boolean invokeInterfaceCount(long userId, long interfaceInfoId);

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b);
}
