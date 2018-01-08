package com.gluxen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gluxen.dao")
public class InquirysystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InquirysystemApplication.class, args);
	}
}
