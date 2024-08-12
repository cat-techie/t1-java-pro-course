package ru.t1.pmorozov.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private String accNumber;
    private BigDecimal balance;
    private ProductType prodType;

    public Product(Long id, String accNumber, BigDecimal balance, ProductType prodType) {
        this.id = id;
        this.accNumber = accNumber;
        this.balance = balance;
        this.prodType = prodType;
    }

}
