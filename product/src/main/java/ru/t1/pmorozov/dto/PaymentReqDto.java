package ru.t1.pmorozov.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentReqDto {
    private Long userId;
    private Long productId;
    private BigDecimal sum;
}
