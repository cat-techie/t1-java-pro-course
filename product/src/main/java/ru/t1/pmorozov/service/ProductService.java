package ru.t1.pmorozov.service;

import org.springframework.stereotype.Service;
import ru.t1.pmorozov.dto.PaymentReqDto;
import ru.t1.pmorozov.dto.PaymentRespDto;
import ru.t1.pmorozov.entity.Product;
import ru.t1.pmorozov.repository.ProductRepo;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Set<Product> getAll() {
        return productRepo.getAll();
    }

    public Optional<Product> get(Long id) {
        return productRepo.get(id);
    }

    public void insert(Product Product) {
        productRepo.insert(Product);
    }

    public void update(Product Product) {
        productRepo.update(Product);
    }

    public void delete(Product Product) {
        productRepo.delete(Product);
    }

    public void deleteAll() {
        productRepo.deleteAll();
    }
}
