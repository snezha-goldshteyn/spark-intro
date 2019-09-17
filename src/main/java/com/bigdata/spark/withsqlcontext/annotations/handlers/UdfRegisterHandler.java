package com.bigdata.spark.withsqlcontext.annotations.handlers;

import com.bigdata.spark.withsqlcontext.annotations.UdfType;
import org.apache.spark.sql.types.DataType;

public interface UdfRegisterHandler {
    UdfType udfType();
    void register(String name, Object o, DataType type);

}
