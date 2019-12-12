package com.lyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Package: com.lyc
 * @Author: Clarence1
 * @Date: 2019/9/29 17:07
 */
@EnableTransactionManagement
@SpringBootApplication
public class NginxApplication {

    public static void main(String[] args) {
        SpringApplication.run(NginxApplication.class, args);
    }

}
