package ru.t1.pmorozov.service;

import org.springframework.stereotype.Service;
import ru.t1.pmorozov.repository.UserRepo;
import ru.t1.pmorozov.entity.User;

import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Set<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }
}
