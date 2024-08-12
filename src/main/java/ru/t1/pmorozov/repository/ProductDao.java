package ru.t1.pmorozov.repository;

import org.springframework.stereotype.Repository;
import ru.t1.pmorozov.data.ProductType;
import ru.t1.pmorozov.data.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ru.t1.pmorozov.repository.Queries.*;


@Repository
public class ProductDao implements Dao<Product> {
    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Product> get(long productId) {
        try (var statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, productId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var product = new Product(
                        productId,
                        resultSet.getString("account_number"),
                        resultSet.getBigDecimal("balance"),
                        ProductType.valueOf(resultSet.getString("product_type"))
                );
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException("Unable to find product by ID: " + ex.getMessage());
        }
    }

    @Override
    public void insert(Product product) {
        try (var statement = connection.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getAccNumber());
            statement.setBigDecimal(2, product.getBalance());
            statement.setString(3, product.getProdType().toString());
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to create product: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Product product) {
        try (var statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setLong(1, product.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to delete product: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try (var statement = connection.prepareStatement(DELETE_ALL_PRODUCTS)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to delete all products: " + ex.getMessage());
        }
    }

    @Override
    public Set<Product> getAll() {
        Set<Product> result = new HashSet<>();
        try (var statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             var resultSet = statement.executeQuery()) {
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
            throw new RuntimeException("Failed to read all products: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public void update(Product product) {
        try (var statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getAccNumber());
            statement.setBigDecimal(2, product.getBalance());
            statement.setString(3, product.getProdType().toString());
            statement.setLong(4, product.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to update product: " + ex.getMessage());
        }
    }

}
