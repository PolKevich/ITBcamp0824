package com.PolKevich.ITBcamp0824.controller;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
public abstract class IntegrationTest {

    private static final MySQLContainer<?> container =
            new MySQLContainer<>("mysql:8.0.24")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @BeforeAll
    static void startContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
