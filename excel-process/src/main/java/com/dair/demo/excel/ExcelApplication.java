package com.dair.demo.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类
 *
 * @author hutao
 * @date 2020年09月12日 20:38:30
 */
@Slf4j
@SpringBootApplication
public class ExcelApplication implements CommandLineRunner {

    /**
     * Main
     */
    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }

}
