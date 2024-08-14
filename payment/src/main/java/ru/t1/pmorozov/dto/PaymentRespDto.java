package ru.t1.pmorozov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentRespDto {
    private Long userId;
    private Long productId;
    private BigDecimal sum;
    private BigDecimal accRestOld;
    private BigDecimal accRestNew;
}
