package com.ts;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author : tsai
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling//开启定时
@Slf4j
@MapperScan(basePackages = {"com.ts.web.mvc.mapper"})
public class ApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ApiApplication.class, args);
        log.info("TSAI - API 启动成功");
        System.out.println("TSAI - API 启动成功 ");
    }
}
