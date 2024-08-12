package ru.t1.pmorozov.service;

import org.springframework.stereotype.Service;
import ru.t1.pmorozov.repository.UserDao;
import ru.t1.pmorozov.data.User;

import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Set<User> getAll() {
        return userDao.getAll();
    }

    public Optional<User> get(Long id) {
        return userDao.get(id);
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }
}
