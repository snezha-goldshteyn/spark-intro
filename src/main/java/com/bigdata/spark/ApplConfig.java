package com.bigdata.spark;

import com.bigdata.spark.popularwords.service.ArtistJudgeImpl;
import com.bigdata.spark.popularwords.service.interfaces.ArtistJudge;
import com.bigdata.spark.popularwords.service.interfaces.PopularWordsService;
import com.bigdata.spark.popularwords.service.PopularWordsServiceImpl;
import com.bigdata.spark.withsqlcontext.annotations.UdfType;
import com.bigdata.spark.withsqlcontext.annotations.handlers.Udf0RegisterHandler;
import com.bigdata.spark.withsqlcontext.annotations.handlers.Udf1RegisterHandler;
import com.bigdata.spark.withsqlcontext.annotations.handlers.UdfRegisterHandler;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Configuration
@PropertySource("classpath:user.properties")
public class ApplConfig {

    @Bean
    public Map<UdfType, UdfRegisterHandler> initMap (List<UdfRegisterHandler> handlers) {
        return handlers.stream().collect(toMap(UdfRegisterHandler::udfType, h -> h));
    }

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

    @Bean
    public ArtistJudge artistJudge() {
        return new ArtistJudgeImpl();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public UdfRegisterHandler udf0RegisterHandler() {
        return new Udf0RegisterHandler();
    }

    @Bean
    public UdfRegisterHandler udf1RegisterHandler() {
        return new Udf1RegisterHandler();
    }

}
