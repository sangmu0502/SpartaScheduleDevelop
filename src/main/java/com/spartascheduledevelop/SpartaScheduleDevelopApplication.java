package com.spartascheduledevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpartaScheduleDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaScheduleDevelopApplication.class, args);
    }

}
