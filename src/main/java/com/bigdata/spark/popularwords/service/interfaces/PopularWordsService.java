package com.bigdata.spark.popularwords.service.interfaces;

import org.apache.spark.api.java.JavaRDD;

import java.io.Serializable;
import java.util.List;

public interface PopularWordsService extends Serializable {

    public List<String> topX (JavaRDD<String> lines, int x);
}
