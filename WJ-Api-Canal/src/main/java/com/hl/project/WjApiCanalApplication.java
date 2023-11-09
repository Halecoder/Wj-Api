package com.hl.project;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo() //开启dubbo
@DubboComponentScan() //dubbo组件扫描，里面可以带basePackages,也可以不用
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WjApiCanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(WjApiCanalApplication.class, args);
    }

}
