package com.hl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.common.BaseResponse;
import com.hl.domain.Poems;

/**
* @author HL
* @description 针对表【poems】的数据库操作Service
* @createDate 2023-11-10 15:39:19
*/
public interface PoemsService extends IService<Poems> {

    Poems getOnePoem();
}
