package com.bigdata.spark.withsqlcontext.annotations.handlers;

import com.bigdata.spark.withsqlcontext.annotations.UdfType;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataType;
import org.springframework.beans.factory.annotation.Autowired;

public class Udf1RegisterHandler implements UdfRegisterHandler {

    @Autowired
    private SQLContext context;

    @Override
    public UdfType udfType() {
        return UdfType.UDF1;
    }

    @Override
    public void register(String name, Object o, DataType type) {
        context.udf().register(name, (UDF1)o, type);
    }


}
