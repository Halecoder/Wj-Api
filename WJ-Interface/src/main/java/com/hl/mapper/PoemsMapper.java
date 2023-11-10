package com.hl.mapper;

import com.hl.domain.Poems;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author HL
* @description 针对表【poems】的数据库操作Mapper
* @createDate 2023-11-10 15:39:19
* @Entity generator.domain.Poems
*/

public interface PoemsMapper extends BaseMapper<Poems> {

    Poems getOnePoem();
}




