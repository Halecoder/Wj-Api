package com.hl.project.controller;


import com.hl.project.common.BaseResponse;
import com.hl.project.common.ResultUtils;
import com.hl.project.service.RankService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Reference(version = "${dubbo.service.version}", check = false)
    private RankService rankService;

        /**
        * @return 获取排行榜
        * 获取前10名
        */
        @GetMapping("/api_rank")
        public BaseResponse<HashMap<String,ArrayList>> getRank() {
//         {"score": 16,"value": "测试接口"}, 转换成 {score: [16],value:["测试接口"]}
            List<HashMap<String,Object>> list = rankService.zReverseRangeWithScore();
            HashMap<String,ArrayList> map = new HashMap<>();
            ArrayList score = new ArrayList();
            ArrayList value = new ArrayList();
            for (HashMap<String,Object> hashMap : list) {
                score.add(hashMap.get("score"));
                value.add(hashMap.get("value"));
            }
            map.put("score",score);
            map.put("value",value);
            return  ResultUtils.success(map);
        }
}
