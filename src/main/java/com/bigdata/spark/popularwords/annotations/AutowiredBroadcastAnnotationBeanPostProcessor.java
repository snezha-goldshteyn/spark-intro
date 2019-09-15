package com.bigdata.spark.popularwords.annotations;

import lombok.SneakyThrows;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

@Component
public class AutowiredBroadcastAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private ApplicationContext context;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(AutowiredBroadcast.class)) {
                ParameterizedType genericType = (ParameterizedType) declaredField.getGenericType();
                Class<?> configClass = (Class<?>) genericType.getActualTypeArguments()[0];
                declaredField.setAccessible(true);
                Object value = context.getBean(configClass);
                Broadcast<Object> broadcast = sc.broadcast(value);
                ReflectionUtils.setField(declaredField, bean, broadcast);
            }
        }
        return bean;
    }
}
