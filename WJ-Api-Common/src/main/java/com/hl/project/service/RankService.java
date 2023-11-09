package com.hl.project.service;

import java.util.Set;

public interface RankService {


    boolean zAdd(String value,double score);

    void zIncreamentScore(String value,double score);

    long zGetRank(String value);

    double zGetScore(String value);


}
