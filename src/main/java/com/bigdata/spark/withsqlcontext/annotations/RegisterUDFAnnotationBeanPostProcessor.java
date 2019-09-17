package com.bigdata.spark.withsqlcontext.annotations;

import com.bigdata.spark.withsqlcontext.annotations.handlers.UdfRegisterHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.spark.sql.types.DataType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RegisterUDFAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<UdfType, UdfRegisterHandler> map;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        RegisterUDF annotation = bean.getClass().getAnnotation(RegisterUDF.class);
        if (annotation != null) {
            String udfName = (String)bean.getClass().getDeclaredMethod("udfName").invoke(bean);
            DataType dataType = annotation.dataType().type;
            UdfType udfType = annotation.udfType();
            UdfRegisterHandler udfRegisterHandler = map.get(udfType);
            udfRegisterHandler.register(udfName, bean, dataType);
        }
        return bean;
    }
}
