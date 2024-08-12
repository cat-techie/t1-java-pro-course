package ru.t1.pmorozov.service;

import org.springframework.stereotype.Service;
import ru.t1.pmorozov.data.Product;
import ru.t1.pmorozov.data.UserProduct;
import ru.t1.pmorozov.repository.UserProductDao;

import java.util.Optional;
import java.util.Set;

@Service
public class UserProductService {
    private final UserProductDao userProductDao;

    public UserProductService(UserProductDao userProductDao) {
        this.userProductDao = userProductDao;
    }

    public Set<UserProduct> getAll() {
        return userProductDao.getAll();
    }

    public Optional<UserProduct> get(Long id) {
        return userProductDao.get(id);
    }

    public void insert(UserProduct userProduct) {
        userProductDao.insert(userProduct);
    }

    public void update(UserProduct userProduct) {
        userProductDao.update(userProduct);
    }

    public void delete(UserProduct userProduct) {
        userProductDao.delete(userProduct);
    }

    public void deleteAll() {
        userProductDao.deleteAll();
    }

    public Set<Product> getProductsByUserId(long userId) {
        return userProductDao.getProductsByUserId(userId);
    }
}
