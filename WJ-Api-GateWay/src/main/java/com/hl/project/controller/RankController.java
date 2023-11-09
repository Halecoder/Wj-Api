package com.hl.project.controller;

import com.hl.project.service.RankService;
import com.hl.project.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    RedisUtil redisUtil;

    public static final String key = "USED_API_RANK";

    /**
     * @return 获取排行榜
     * 获取前10名
     */
    @RequestMapping("/get_rank")
    public Object getRank() {
        return redisUtil.zReverseRangeWithScore(key);
    }
}
