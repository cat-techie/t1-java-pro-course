package ru.t1.pmorozov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1.pmorozov.service.HikariService;

import java.sql.Connection;

@Configuration
public class AppConfig {

    @Bean
    public static Connection connection(){
        try {
            return HikariService.getDs().getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create connection to DB: " + e.getMessage());
        }
    }
}
