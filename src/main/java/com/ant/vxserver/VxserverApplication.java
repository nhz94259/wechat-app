package com.ant.vxserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication
@MapperScan(basePackages = "com.ant.vxserver.mapper")
@ComponentScan(basePackages = "com.ant.vxserver")
public class VxserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(VxserverApplication.class, args);
	}
}
