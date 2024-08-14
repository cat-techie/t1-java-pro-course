package ru.t1.pmorozov.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class PaymentReqDto {
    private Long userId;
    private Long productId;
    private BigDecimal sum;
}
