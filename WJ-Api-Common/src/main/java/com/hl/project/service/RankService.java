package com.hl.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RankService {


    boolean zAdd(String value,double score);

    void zIncreamentScore(String value,double score);

    long zGetRank(String value);

    double zGetScore(String value);


    List<HashMap<String,Object>> zReverseRangeWithScore();
}
