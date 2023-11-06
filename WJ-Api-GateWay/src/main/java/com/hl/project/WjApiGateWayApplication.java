package com.hl.project;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WjApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WjApiGateWayApplication.class, args);
	}

}
