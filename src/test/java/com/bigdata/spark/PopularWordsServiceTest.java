package com.bigdata.spark;

import com.bigdata.spark.popularwords.service.interfaces.PopularWordsService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplConfig.class)
public class PopularWordsServiceTest {

    @Autowired
    JavaSparkContext sc;

    @Autowired
    PopularWordsService popularWordsService;

    @Test
    public void testTopX() throws Exception {
        JavaRDD<String> rdd = sc.textFile("/Users/bodiabuzynovskyi/Desktop/Snezha/sample.txt");
        List<String> top1 = popularWordsService.topX(rdd, 1);
        assertEquals("java", top1.get(0));
    }
}