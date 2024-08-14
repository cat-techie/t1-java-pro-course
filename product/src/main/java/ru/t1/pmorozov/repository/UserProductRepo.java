package ru.t1.pmorozov.repository;

import org.springframework.stereotype.Repository;
import ru.t1.pmorozov.entity.Product;
import ru.t1.pmorozov.entity.ProductType;
import ru.t1.pmorozov.entity.UserProduct;
import ru.t1.pmorozov.exceptions.RepoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ru.t1.pmorozov.repository.Queries.*;

@Repository
public class UserProductRepo implements Repo<UserProduct> {

    private final Connection connection;

    public UserProductRepo(Connection connection) {
        this.connection = connection;
    }

    public Set<Product> getProductsByUserId(long userId) {
        Set<Product> result = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_PRODUCTS_BY_USER_ID)) {
            statement.setLong(1, userId);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("account_number"),
                        resultSet.getBigDecimal("balance"),
                        ProductType.valueOf(resultSet.getString("product_type"))
                );
                result.add(product);
            }
        } catch (SQLException ex) {
            throw new RepoException("Failed to get products by user_id: " + ex.getMessage());
        }

        return result;
    }

    @Override
    public void insert(UserProduct userProduct) {
        try (var statement = connection.prepareStatement(INSERT_USER_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userProduct.getUserId());
            statement.setLong(2, userProduct.getProductId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to create userProduct: " + ex.getMessage());
        }
    }

    @Override
    public void delete(UserProduct userProduct) {
        try (var statement = connection.prepareStatement(DELETE_USER_PRODUCT)) {
            statement.setLong(1, userProduct.getProductId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to delete userProduct: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (var statement = connection.prepareStatement(DELETE_ALL_USER_PRODUCTS)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to delete all users: " + ex.getMessage());
        }
    }

    @Override
    public void update(UserProduct userProduct) {
        try (var statement = connection.prepareStatement(UPDATE_USER_PRODUCT)) {
            statement.setLong(1, userProduct.getUserId());
            statement.setLong(2, userProduct.getProductId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RepoException("Failed to update userProduct: " + ex.getMessage());
        }
    }

    @Override
    public Optional<UserProduct> get(long id) {
        try (var statement = connection.prepareStatement(FIND_USER_PRODUCT_BY_PRODUCT_ID)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var userProduct = new UserProduct();
                userProduct.setProductId(id);
                userProduct.setUserId(resultSet.getLong("user_id"));
                return Optional.of(userProduct);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RepoException("Unable to find userProduct by ID: " + ex.getMessage());
        }
    }

    @Override
    public Set<UserProduct> getAll() {
        Set<UserProduct> result = new HashSet<>();
        try (var statement = connection.prepareStatement(SELECT_ALL_USER_PRODUCTS);
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var userProduct = new UserProduct();
                userProduct.setProductId(resultSet.getLong("id"));
                userProduct.setUserId(resultSet.getLong("user_id"));
                result.add(userProduct);
            }
        } catch (SQLException ex) {
            throw new RepoException("Failed to read all userProducts: " + ex.getMessage());
        }
        return result;
    }
}
