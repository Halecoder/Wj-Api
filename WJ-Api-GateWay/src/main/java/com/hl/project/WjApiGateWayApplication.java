package com.hl.project;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan(basePackages = "com.hl.project.dao")
public class WjApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WjApiGateWayApplication.class, args);
	}

}
