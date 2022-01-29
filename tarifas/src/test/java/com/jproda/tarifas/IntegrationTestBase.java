package com.jproda.tarifas;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
@Sql("/init-db.sql")
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class IntegrationTestBase {
        private static PostgreSQLContainer database = new PostgreSQLContainer("postgres:11.1");

        static {
            database.start();
        }

        @DynamicPropertySource
        static void databaseProperties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", database::getJdbcUrl);
            registry.add("spring.datasource.username", database::getUsername);
            registry.add("spring.datasource.password", database::getPassword);

        }

    }

