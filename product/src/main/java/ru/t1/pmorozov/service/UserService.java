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

    public Set<User> getAll() {
        return userRepo.getAll();
    }

    public Optional<User> get(Long id) {
        return userRepo.get(id);
    }

    public void insert(User user) {
        userRepo.insert(user);
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }
}
