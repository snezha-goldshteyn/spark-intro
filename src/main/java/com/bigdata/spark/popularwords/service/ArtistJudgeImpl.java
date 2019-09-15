package com.bigdata.spark.popularwords.service;

import com.bigdata.spark.popularwords.service.interfaces.ArtistJudge;
import com.bigdata.spark.popularwords.service.interfaces.PopularWordsService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistJudgeImpl implements ArtistJudge {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private PopularWordsService popularWordsService;

    @Override
    public List<String> topX(String artist, int x) {
        JavaRDD<String> rdd = sc.textFile("/Users/snezhana/Desktop/songs/" + artist + "/*");
        return popularWordsService.topX(rdd, x);
    }

    @Override
    public int compare(String artist1, String artist2, int x) {
        return 0;
    }
}
