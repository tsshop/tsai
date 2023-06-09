package com.ts;

import lombok.extern.slf4j.Slf4j;
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
public class AdminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AdminApplication.class, args);
        log.info("TSAI项目启动成功");
    }
}
