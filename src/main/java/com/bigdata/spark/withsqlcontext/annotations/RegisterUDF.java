package com.bigdata.spark.withsqlcontext.annotations;

import org.apache.spark.sql.types.DataTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterUDF {
    ReturnDataTypes dataType();
    UdfType udfType();
}
