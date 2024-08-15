package ru.t1.pmorozov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.t1.pmorozov.entity.Product;
import java.util.Set;


public interface ProductRepo extends CrudRepository<Product, Long> {
    @Override
    Set<Product> findAll();

}
