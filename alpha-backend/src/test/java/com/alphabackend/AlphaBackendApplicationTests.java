package com.alphabackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = AlphaBackendApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
@ComponentScan(basePackages = "com.alphabackend")
class AlphaBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
