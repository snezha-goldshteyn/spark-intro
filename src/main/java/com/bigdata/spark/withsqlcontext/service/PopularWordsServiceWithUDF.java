package com.bigdata.spark.withsqlcontext.service;

import com.bigdata.spark.withsqlcontext.utils.GarbageFilter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.apache.spark.sql.functions.*;

@Service
public class PopularWordsServiceWithUDF implements PopularWordsServiceUDF {
    @Autowired
    private GarbageFilter filter;

    @Autowired
    private SQLContext context;

    @PostConstruct
    public void registerUdf() {
        context.udf().register(filter.udfName(), filter, DataTypes.BooleanType);
    }

    @Override
    public List<String> mostUsedWords(Dataset<Row> lines, int amount) {
        Dataset<Row> sorted = lines.withColumn("words", lower(col("words")))
                .filter(callUDF(filter.udfName(), col("words")))
                .groupBy(col("words")).agg(count("words").as("count"))
                .sort(col("count").desc());
        sorted.show();
        Row[] rows = (Row[]) sorted.take(amount);
        List<String> topX = new ArrayList<>();
        for (Row row: rows) {
            topX.add(row.getString(0));
        }
        return topX;
    }
}
