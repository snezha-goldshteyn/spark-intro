package com.bigdata.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class SparkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparkApplication.class);
    }

}
