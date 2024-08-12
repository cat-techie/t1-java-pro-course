package ru.t1.pmorozov.service;

import org.springframework.stereotype.Service;
import ru.t1.pmorozov.data.Product;
import ru.t1.pmorozov.repository.ProductDao;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Set<Product> getAll() {
        return productDao.getAll();
    }

    public Optional<Product> get(Long id) {
        return productDao.get(id);
    }

    public void insert(Product Product) {
        productDao.insert(Product);
    }

    public void update(Product Product) {
        productDao.update(Product);
    }

    public void delete(Product Product) {
        productDao.delete(Product);
    }

    public void deleteAll() {
        productDao.deleteAll();
    }
}
