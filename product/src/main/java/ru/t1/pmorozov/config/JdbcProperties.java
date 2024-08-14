package ru.t1.pmorozov.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

}
