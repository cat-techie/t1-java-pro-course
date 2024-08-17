package ru.t1.pmorozov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LimitRespDto {
    private Long userId;
    private BigDecimal oldValue;
    private BigDecimal newValue;
}
