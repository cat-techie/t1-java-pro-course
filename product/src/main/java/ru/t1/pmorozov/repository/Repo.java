package ru.t1.pmorozov.repository;

import java.util.Optional;
import java.util.Set;

public interface Repo<T> {

    Optional<T> get(long id);

    Set<T> getAll();

    void insert(T t);

    void update(T t);

    void delete(T t);

    void deleteAll();
}
