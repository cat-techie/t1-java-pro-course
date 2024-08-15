package ru.t1.pmorozov.service;

import org.springframework.stereotype.Service;
import ru.t1.pmorozov.entity.Product;
import ru.t1.pmorozov.repository.ProductRepo;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Set<Product> findAll() {
        return productRepo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public void delete(Product Product) {
        productRepo.delete(Product);
    }

    public void deleteAll() {
        productRepo.deleteAll();
    }
}
