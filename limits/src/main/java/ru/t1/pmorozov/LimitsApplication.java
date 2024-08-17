package ru.t1.pmorozov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.t1.pmorozov.properties.LimitProperties;

@SpringBootApplication
@EnableConfigurationProperties({LimitProperties.class})
public class LimitsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LimitsApplication.class, args);
    }
}