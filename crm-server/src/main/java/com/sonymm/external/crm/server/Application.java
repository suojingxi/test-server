package com.sonymm.external.crm.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sonymm.external.crm.server")
@EnableAutoConfiguration
public class Application{

	//启动spring boot的入口
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}