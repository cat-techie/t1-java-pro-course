package ru.t1.pmorozov.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(ProductsRestTemplateProperties properties, RestTemplateResponseErrorHandler errorHandler) {
        return new RestTemplateBuilder()
                .rootUri(properties.getUrl())
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
