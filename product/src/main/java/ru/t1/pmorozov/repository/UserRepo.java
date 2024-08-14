package ru.t1.pmorozov.repository;

import org.springframework.stereotype.Repository;
import ru.t1.pmorozov.entity.User;
import ru.t1.pmorozov.exceptions.RepoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ru.t1.pmorozov.repository.Queries.*;

@Repository
public class UserRepo implements Repo<User> {

    private final Connection connection;

    public UserRepo(Connection connection) {
        this.connection = connection;
    }

    public void insert(User user) {
        try (var statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            throw new RepoException("Failed to create user: " + ex.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try (var statement = connection.prepareStatement(DELETE_USER)) {
            statement.setLong(1, user.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to delete user: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (var statement = connection.prepareStatement(DELETE_ALL_USERS)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to delete all users: " + ex.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try (var statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUserName());
            statement.setLong(2, user.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to update user: " + ex.getMessage());
        }
    }

    @Override
    public Optional<User> get(long id) {
        try (var statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var user = new User();
                user.setId(id);
                user.setUserName(resultSet.getString("username"));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RepoException("Unable to find user by ID: " + ex.getMessage());
        }
    }

    @Override
    public Set<User> getAll() {
        Set<User> result = new HashSet<>();
        try (var statement = connection.prepareStatement(SELECT_ALL_USERS);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("username"));
                result.add(user);
            }
        } catch (SQLException ex) {
            throw new RepoException("Failed to read all users: " + ex.getMessage());
        }
        return result;
    }

}
