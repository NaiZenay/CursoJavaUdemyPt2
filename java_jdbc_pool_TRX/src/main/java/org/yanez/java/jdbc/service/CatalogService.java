package org.yanez.java.jdbc.service;

import org.yanez.java.jdbc.modelo.Category;
import org.yanez.java.jdbc.modelo.CategoryRepository;
import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogService implements Service {
    private Repository<Product> productRepository;
    private Repository<Category> categoryRepository;

    public CatalogService() {
        this.productRepository = new ProductRepository();
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    public List<Product> list() throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            productRepository.setConn(connection);
            return productRepository.listAll();
        }
    }

    @Override
    public Product byID(Long id) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            productRepository.setConn(connection);
            return productRepository.byId(id);
        }
    }

    @Override
    public Product register(Product product) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            productRepository.setConn(connection);
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            Product product1 = null;
            try {
                product1 = productRepository.register(product);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return product1;
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            productRepository.setConn(connection);
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                productRepository.delete(id);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Category> listCategories() throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            categoryRepository.setConn(connection);
            return categoryRepository.listAll();
        }
    }

    @Override
    public Category categorybyID(Long id) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            categoryRepository.setConn(connection);
            return categoryRepository.byId(id);
        }
    }

    @Override
    public Category registerCategory(Category category) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            categoryRepository.setConn(connection);
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            Category category1 = null;
            try {
                category1 = categoryRepository.register(category);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return category1;
        }
    }

    @Override
    public void deleteCategory(Long id) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            categoryRepository.setConn(connection);
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                categoryRepository.delete(id);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerProductANDCategory(Product product, Category category) throws SQLException {
        try (Connection connection = ConnectionDB.getConnection()) {
            productRepository.setConn(connection);
            categoryRepository.setConn(connection);

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            Category category1 = null;
            try {
                category1=categoryRepository.register(category);
                product.setCategory(category1);
                product=productRepository.register(product);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
