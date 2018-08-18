package com.ant.vxserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ant")
public class VxserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(VxserverApplication.class, args);
	}
}
