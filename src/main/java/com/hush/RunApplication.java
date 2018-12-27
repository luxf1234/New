package com.hush;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hush.mapper")		//扫描Mybatis接口文件
/*@ServletComponentScan("com.hush.configuration")
@ComponentScan("com.hush.configuration")*///不要写
public class RunApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RunApplication.class);
    }

	
	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
		
	}
}