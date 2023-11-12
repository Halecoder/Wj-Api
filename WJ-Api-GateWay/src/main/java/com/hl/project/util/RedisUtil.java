package com.hl.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 有序集合添加之前没有的元素
     */
    public boolean zAdd(String key,Object value,double score) {
        return redisTemplate.opsForZSet().add(key, value,score);
    }

    /**
     * 获取集合中元素的排名（从大到小排序）
     * @param key
     * @param value
     * @return
     */
    public long zGetRank(String key,Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 若集合中已有此元素，则此元素score+传入参数
     * 若没有此元素，则创建元素。
     * @param key
     * @param value
     * @param score
     */
    public void zIncreamentScore(String key,Object value,double score) {
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }


    /**
     * 对集合按照分数从小到大排序（默认）
     * 指定位置区间0，-1指排序所有元素
     * 得到的值带有score
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScore(String key) {
        return redisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
    }

    /**
     * 对集合按照分数从大到小排序
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeWithScore(String key){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 10);
    }

    /**
     * 获取有序集合的大小
     * @param key
     * @return
     */
    public Long zGetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取key集合里面，value值的分数
     * @param key
     * @param value
     * @return
     */
    public double zGetScoreByValue(String key,Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }


    /**
     * 指定分数区间，从大到小排序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(String key, double start, double end){
        Set<ZSetOperations.TypedTuple<Object>> sets = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, start, end);
        return sets;
    }

    /**
     * 删除集合中的元素
     *
     * @param key
     * @param value
     * @return
     */
    public Long zRemove(String key, String value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

}
