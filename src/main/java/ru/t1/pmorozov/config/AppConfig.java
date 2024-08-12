package ru.t1.pmorozov.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        try {
            var config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            config.setUsername("pmorozov");
            config.setPassword("password");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            return new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Hikari DataSource bean: " + e.getMessage());
        }
    }

    @Bean
    public static Connection connection(DataSource dataSource){
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create connection to DB: " + e.getMessage());
        }
    }
}
