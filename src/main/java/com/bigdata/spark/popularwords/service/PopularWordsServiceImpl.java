package com.bigdata.spark.popularwords.service;

import com.bigdata.spark.popularwords.annotations.AutowiredBroadcast;
import com.bigdata.spark.popularwords.service.interfaces.PopularWordsService;
import com.bigdata.spark.popularwords.utils.UserConfig;
import com.bigdata.spark.popularwords.utils.WordsUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.List;

//@Service
public class PopularWordsServiceImpl implements PopularWordsService {

    @AutowiredBroadcast
    private Broadcast<UserConfig> userConfig;

    @Override
    public List<String> topX(JavaRDD<String> lines, int x) {
        return lines.map(String::toLowerCase)
                .flatMap(WordsUtil::getWords)
                .filter(word -> !this.userConfig.value().garbage.contains(word))
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .map(Tuple2::_2)
                .take(x);
    }
}
