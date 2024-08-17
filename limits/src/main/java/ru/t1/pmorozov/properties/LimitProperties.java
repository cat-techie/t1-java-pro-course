package ru.t1.pmorozov.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Data
@ConfigurationProperties(prefix = "limits")
public class LimitProperties {
    private BigDecimal minValue;
    private BigDecimal maxIncrease;
    private Long updateDays;
}
