package ru.t1.pmorozov.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProduct {
    private Long productId;
    private Long userId;

    public UserProduct(Long productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }
}
