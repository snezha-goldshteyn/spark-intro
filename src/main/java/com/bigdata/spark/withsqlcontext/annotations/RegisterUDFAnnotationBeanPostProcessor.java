package com.bigdata.spark.withsqlcontext.annotations;

import lombok.SneakyThrows;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class RegisterUDFAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private SQLContext context;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RegisterUDF.class)) {
            Method udfName = bean.getClass().getDeclaredMethod("udfName");
            context.udf().register((String) udfName.invoke(bean), (UDF1)bean, DataTypes.BooleanType);
        }
        return bean;
    }
}
