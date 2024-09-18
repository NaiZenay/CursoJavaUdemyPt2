package org.yanez.java.jdbc.service;

import org.yanez.java.jdbc.modelo.Category;
import org.yanez.java.jdbc.modelo.Product;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Product> list() throws SQLException;

    Product byID(Long id) throws SQLException;

    Product register(Product product) throws SQLException;

    void delete(Long id) throws SQLException;

    List<Category> listCategories() throws SQLException;

    Category categorybyID(Long id) throws SQLException;

    Category registerCategory(Category category) throws SQLException;

    void deleteCategory(Long id) throws SQLException;

    void registerProductANDCategory(Product product, Category category) throws SQLException;
}
