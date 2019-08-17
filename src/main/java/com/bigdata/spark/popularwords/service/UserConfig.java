package com.bigdata.spark.popularwords.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component
public class UserConfig implements Serializable {
    public List<String> garbage;

    @Value("${garbage}")
    private void setGarbage(String garbage) {
        this.garbage = Arrays.asList(garbage.split(","));
    }

}
