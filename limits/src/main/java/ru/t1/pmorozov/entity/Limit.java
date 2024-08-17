package ru.t1.pmorozov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "limits")
@NoArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "current_value")
    private BigDecimal currentValue;
    @Column(name = "max_value")
    private BigDecimal maxValue;
    @Column(name = "update_date")
    private LocalDate updateDate;

    public Limit(Long userId, BigDecimal currentValue, BigDecimal maxValue, LocalDate updateDate) {
        this.userId = userId;
        this.currentValue = currentValue;
        this.maxValue = maxValue;
        this.updateDate = updateDate;
    }
}
