package com.bigdata.spark.withsqlcontext.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class MainExample {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().
                appName("linkedIn")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataset = sparkSession.read().json("profiles.json");
        dataset.show();
        dataset = dataset.withColumn("salary", col("age").multiply(10)
                .multiply(size(col("keywords"))).multiply(2));
        dataset.show();
        Dataset<Row> sorted = dataset.withColumn("keyword", explode(col("keywords"))).select("keyword")
                .groupBy("keyword").agg(count("keyword").as("amount"))
                .sort(col("amount").desc());
        sorted.show();
        String mostPopular = sorted.head().getAs("keyword");
        System.out.println("most popular: " + mostPopular);
        dataset.filter(array_contains(col("keywords"), mostPopular)).where(col("salary").leq(2000)).show();

    }

//    SparkConf sparkConf = new SparkConf().setAppName("linkedIn").setMaster("local[*]");
//    JavaSparkContext sc = new JavaSparkContext(sparkConf);
//    SQLContext sqlContext = SparkSession.builder();
}
