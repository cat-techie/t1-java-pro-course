package ru.t1.pmorozov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.t1.pmorozov.entity.UserProduct;
import java.util.Set;


public interface UserProductRepo extends CrudRepository<UserProduct, Long> {

    @Override
    Set<UserProduct> findAll();

    Set<UserProduct> findByUserId(Long userId);
}
