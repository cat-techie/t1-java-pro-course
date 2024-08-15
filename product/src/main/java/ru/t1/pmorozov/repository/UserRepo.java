package ru.t1.pmorozov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.t1.pmorozov.entity.User;

import java.util.Set;


public interface UserRepo extends CrudRepository<User, Long> {
    @Override
    Set<User> findAll();
}
