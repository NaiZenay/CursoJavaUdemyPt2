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
    public Product byId(Long id) {
        Product product = null;
        try (PreparedStatement preparedStatement = getConncection().prepareStatement("SELECT * FROM productos WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                product = mapProduct(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void register(Product product) {
        String sql;

        if (product.getId() > 0 && product.getId() != null) {
            sql = "UPDATE prodcutos SET nombre=?, precio=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos (nombre,precio,fecha_registro) VALUES(?,?,?)";
        }

        try (PreparedStatement preparedStatement = getConncection().prepareStatement(sql)) {
            preparedStatement.setString(1, product.getNombre());
            preparedStatement.setLong(2, product.getPrecio());
            if (product.getId() > 0 && product.getId() != null) {
                preparedStatement.setLong(3, product.getId());
            } else {
                preparedStatement.setDate(3, new Date(product.getFecha_registro().getTime()));

            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = getConncection().prepareStatement("DELETE FROM productos EHre id=?")) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
