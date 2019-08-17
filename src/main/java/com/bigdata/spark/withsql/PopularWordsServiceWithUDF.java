//package com.bigdata.spark;
//
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.sql.DataFrame;
//import org.apache.spark.sql.SQLContext;
//import org.apache.spark.sql.types.DataTypes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//import static org.apache.spark.sql.functions.col;
//
//@Service
//public class PopularWordsServiceWithUDF implements PopularWordsServiceUDF {
//    @Autowired
//    private GarbageFilter filter;
//
//    @Autowired
//    private SQLContext sqlContext;
//
//    @PostConstruct
//    public void registerUdf() {
//        sqlContext.udf().register(filter.udfName(), filter, DataTypes.BooleanType);
//    }
//
//    @Override
//    public List<String> mostUsedWords(DataFrame dataFrame, int amount) {
//        DataFrame sorted = dataFrame.withColumn("words", col("words")))
//                .filter(callUDF(filter.udfName(), col("words")))
//        return null;
//    }
//}
