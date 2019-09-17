package com.bigdata.spark.withsqlcontext.utils;

import com.bigdata.spark.popularwords.utils.UserConfig;
import com.bigdata.spark.withsqlcontext.annotations.RegisterUDF;
import com.bigdata.spark.withsqlcontext.annotations.ReturnDataTypes;
import com.bigdata.spark.withsqlcontext.annotations.UdfType;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RegisterUDF(udfType = UdfType.UDF1, dataType = ReturnDataTypes.BOOLEAN)
public class GarbageFilter implements UDF1<String, Boolean> {

    @Autowired
    private UserConfig config;

    public String udfName() {return "notGarbage";}

    @Override
    public Boolean call(String word) throws Exception {
        return !config.garbage.contains(word);
    }
}
