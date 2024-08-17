package ru.t1.pmorozov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitReqDto {
    private Long userId;
    private BigDecimal sum;
}
