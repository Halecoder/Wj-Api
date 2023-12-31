package com.hl.project.service.impl;

import com.hl.project.service.RankService;
import com.hl.project.util.RedisUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.*;

@Service(version = "${dubbo.service.version}")
public class RankServiceImpl implements RankService {
    @Resource
    RedisUtil redisUtil;

    public static final String key = "USED_API_RANK";

    /**
     * 向排行榜里面添加数据，value是用户信息，demo里面村的是user_id，socre是用户的分数
     */
    @Override
    public boolean zAdd(String value, double score) {
        return redisUtil.zAdd(key, value, score);
    }

    /**
     * 增加用户的积分
     */
    @Override
    public void zIncreamentScore(String value, double score) {
        redisUtil.zIncreamentScore(key, value, score);
    }

    /**
     * 获取用户的排名
     */
    @Override
    public long zGetRank(String value) {
        return redisUtil.zGetRank(key, value);
    }

    /**
     * 获取用户的分数
     */
    @Override
    public double zGetScore(String value) {
        return redisUtil.zGetScoreByValue(key, value);
    }

    /**
     * @param
     * @return
     *  获取排行榜
     */
    @Override
    public List<HashMap<String,Object>> zReverseRangeWithScore() {
        Set<ZSetOperations.TypedTuple<Object>> sets =  redisUtil.zReverseRangeWithScore(key);
        List<HashMap<String,Object>> map = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> set : sets) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("value",set.getValue());
            hashMap.put("score",set.getScore());
            map.add(hashMap);
        }
        return map;

    }


    /**
     * @param
     * @return
     *  删除指定数据
     */
    @Override
    public long zremove(String value) {
        return redisUtil.zRemove(key,value);
    }

}
