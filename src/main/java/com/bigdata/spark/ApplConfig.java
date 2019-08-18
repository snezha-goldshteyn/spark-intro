package com.bigdata.spark;

import com.bigdata.spark.popularwords.service.interfaces.PopularWordsService;
import com.bigdata.spark.popularwords.service.PopularWordsServiceImpl;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:user.properties")
public class ApplConfig {

    @Bean
    public PopularWordsService popularWordsService() {
        return new PopularWordsServiceImpl();
    }

    @Bean
    public JavaSparkContext sc() {
        SparkConf conf = new SparkConf();
        conf.setAppName("music analyst");
        conf.setMaster("local[1]");
        return new JavaSparkContext(conf);
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(sc());
    }

//    @Bean
//    public ArtistJudge artistJudge() {
//        return new ArtistJudgeImpl();
//    }

//    @Bean
//    public UserConfig userConfig() {
//        return new UserConfig();
//    }

    @Bean
    public PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @Bean
//    public CompareController compareController() {
//        return new CompareController();
//    }
}
