package org.yanez.java.jdbc.modelo;

import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product> {

    private Connection getConncection() throws SQLException {
        return ConnectionDB.getInstance();
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        try (
                Statement statement = getConncection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM productos")
        ) {
            while (resultSet.next()) {
                Product product = mapProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product byId(Long id) throws SQLException {
        Product product = null;
        try (PreparedStatement preparedStatement = getConncection().prepareStatement("SELECT * FROM productos WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                product=mapProduct(resultSet);
        } catch (SQLException e) {

        }
        return product;
    }

    @Override
    public void register(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    private static Product mapProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setNombre(resultSet.getString("nombre"));
        product.setPrecio(resultSet.getInt("precio"));
        product.setFecha_registro(resultSet.getDate("fecha_registro"));
        return product;
    }
}
