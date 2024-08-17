package ru.t1.pmorozov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.t1.pmorozov.entity.Limit;

import java.util.Set;


public interface LimitRepo extends CrudRepository<Limit, Long> {
    @Override
    Set<Limit> findAll();

    Limit findByUserId(Long userId);
}
