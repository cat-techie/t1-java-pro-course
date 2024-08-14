package ru.t1.pmorozov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.pmorozov.entity.Product;
import ru.t1.pmorozov.entity.ProductType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String accNumber;
    private BigDecimal balance;
    private ProductType prodType;

    public ProductDto(Long id, String accNumber, BigDecimal balance, ProductType prodType) {
        this.id = id;
        this.accNumber = accNumber;
        this.balance = balance;
        this.prodType = prodType;
    }

    public static Set<ProductDto> getSetProductDto(Set<Product> productSet) {
        Set<ProductDto> productDtoSet = new HashSet<>();
        for (Product product : productSet) {
            productDtoSet.add(new ProductDto(
                    product.getId(),
                    product.getAccNumber(),
                    product.getBalance(),
                    product.getProdType()
            ));
        }
        return productDtoSet;
    }

}
