package com.mapsharp.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@MapperScan(basePackages = "com.mapsharp.springboot2.mapper")//指定要扫描的mybatis Mapper类的包的路径
@SpringBootApplication
public class Springboot2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2Application.class, args);
	}

}
