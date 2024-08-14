package ru.t1.pmorozov.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.t1.pmorozov.dto.PaymentReqDto;
import ru.t1.pmorozov.dto.PaymentRespDto;
import ru.t1.pmorozov.dto.ProductDto;
import ru.t1.pmorozov.entity.Product;
import ru.t1.pmorozov.entity.UserProduct;
import ru.t1.pmorozov.exceptions.PaymentRespException;
import ru.t1.pmorozov.exceptions.ProductRespException;
import ru.t1.pmorozov.repository.ProductRepo;
import ru.t1.pmorozov.repository.UserProductRepo;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class UserProductService {
    private final UserProductRepo userProductRepo;
    private final ProductRepo productRepo;

    public UserProductService(UserProductRepo userProductRepo, ProductRepo productRepo) {
        this.userProductRepo = userProductRepo;
        this.productRepo = productRepo;
    }

    public Set<UserProduct> getAll() {
        return userProductRepo.getAll();
    }

    public Optional<UserProduct> get(Long id) {
        return userProductRepo.get(id);
    }

    public void insert(UserProduct userProduct) {
        userProductRepo.insert(userProduct);
    }

    public void update(UserProduct userProduct) {
        userProductRepo.update(userProduct);
    }

    public void delete(UserProduct userProduct) {
        userProductRepo.delete(userProduct);
    }

    public void deleteAll() {
        userProductRepo.deleteAll();
    }

    public Set<ProductDto> getProductsByUserId(long userId) {
        return ProductDto.getSetProductDto(userProductRepo.getProductsByUserId(userId));
    }

    public PaymentRespDto doPayment(PaymentReqDto payReqDto) {
        Long userId = payReqDto.getUserId();
        Long productId = payReqDto.getProductId();
        BigDecimal sum = payReqDto.getSum();

        Product product = productRepo.get(productId).orElseThrow();

        if (getProductsByUserId(userId).stream().noneMatch(p -> p.getId().equals(productId))) {
            throw new ProductRespException(String.format("The product %s does not belong to the user %s", productId, userId), HttpStatus.BAD_REQUEST);
        }

        BigDecimal balance = product.getBalance();
        if (balance == null || balance.compareTo(sum) < 0) {
            throw new PaymentRespException(String.format("There are not enough funds in the account %s for the product %s", product.getAccNumber(), productId), HttpStatus.BAD_REQUEST);
        }

        BigDecimal oldRest = product.getBalance();
        BigDecimal newRest = balance.subtract(sum);
        product.setBalance(newRest);
        productRepo.update(product);

        return new PaymentRespDto(userId, productId, sum, oldRest, newRest);
    }
}
