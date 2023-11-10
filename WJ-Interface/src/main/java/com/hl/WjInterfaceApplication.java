package com.hl;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hl.mapper")
public class WjInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WjInterfaceApplication.class, args);
	}

}
