package com.bigdata.spark.withsql;

import org.apache.spark.sql.DataFrame;

import java.util.List;

public interface PopularWordsServiceUDF {
    public List<String> mostUsedWords(DataFrame dataFrame, int amount);
}
