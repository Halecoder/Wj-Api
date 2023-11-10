package com.hl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.domain.Poems;
import com.hl.service.PoemsService;
import com.hl.mapper.PoemsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author HL
* @description 针对表【poems】的数据库操作Service实现
* @createDate 2023-11-10 15:39:19
*/
@Service
public class PoemsServiceImpl extends ServiceImpl<PoemsMapper, Poems>
    implements PoemsService {

    @Resource
    private PoemsMapper poemsMapper;

    @Override
    public Poems getOnePoem() {
        //随机获取一首诗
        return poemsMapper.getOnePoem();
    }
}




