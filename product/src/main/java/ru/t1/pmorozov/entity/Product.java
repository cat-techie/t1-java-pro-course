package ru.t1.pmorozov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_number")
    private String accNumber;
    @Column(name = "balance")
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType prodType;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "product_id", nullable = false)
    private UserProduct userProduct;
}
