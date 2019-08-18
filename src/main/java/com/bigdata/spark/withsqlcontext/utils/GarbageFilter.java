package com.bigdata.spark.withsqlcontext.utils;

import com.bigdata.spark.popularwords.utils.UserConfig;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GarbageFilter implements UDF1<String, Boolean> {
    @Autowired
    private UserConfig config;

    public String udfName() {return "notGarbage";}

    @Override
    public Boolean call(String word) throws Exception {
        return !config.garbage.contains(word);
    }
}
