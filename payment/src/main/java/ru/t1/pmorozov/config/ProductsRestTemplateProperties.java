package ru.t1.pmorozov.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "products-ms")
public class ProductsRestTemplateProperties {
    private String url;
    private Duration connectTimeout;
    private Duration readTimeout;
}
