package com.bigdata.spark.withsql;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public class GarbageFilter implements UDF1<String, Boolean> {
    @Value("${garbage}")
    private String garbage;

    public String udfName() {return "notGarbage";}

    @Override
    public Boolean call(String word) throws Exception {
        return !Arrays.asList(garbage.split(",")).contains(word);
    }
}
