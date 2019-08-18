package com.bigdata.spark.withsqlcontext.service;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

public interface PopularWordsServiceUDF {
    public List<String> mostUsedWords(Dataset<Row> dataset, int amount);
}
