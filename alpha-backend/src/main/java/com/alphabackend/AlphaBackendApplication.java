package com.alphabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AlphaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaBackendApplication.class, args);
    }

}
