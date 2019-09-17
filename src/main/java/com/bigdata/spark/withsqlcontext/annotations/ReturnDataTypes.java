package com.bigdata.spark.withsqlcontext.annotations;

import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

public enum ReturnDataTypes {
    STRING(DataTypes.StringType),
    BINARY(DataTypes.BinaryType),
    BOOLEAN(DataTypes.BooleanType),
    DATE(DataTypes.DateType),
    TIMESTAMP(DataTypes.TimestampType),
    CALENDAR(DataTypes.CalendarIntervalType),
    DOUBLE(DataTypes.DoubleType),
    FLOAT(DataTypes.FloatType),
    BYTE(DataTypes.ByteType),
    INTEGER(DataTypes.IntegerType),
    LONG(DataTypes.LongType),
    SHORT(DataTypes.ShortType),
    NULL(DataTypes.NullType);

    DataType type;

    ReturnDataTypes(DataType type) {
        this.type = type;
    }
}
