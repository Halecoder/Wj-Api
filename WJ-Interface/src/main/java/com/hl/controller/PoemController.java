package com.hl.controller;


import com.hl.common.BaseResponse;
import com.hl.domain.Poems;
import com.hl.service.PoemsService;
import com.hl.utils.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/poem")
public class PoemController {

    @Resource
    private PoemsService poemsService;

    //随机获取一首诗
    @RequestMapping("/getOnePoem")
    public BaseResponse<Poems> getOnePoem() {
        return ResultUtils.success(poemsService.getOnePoem());
    }


}
