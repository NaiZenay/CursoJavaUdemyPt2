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
                ResultSet resultSet = statement.executeQuery("SELECT p.*,c.nombre as categoria  FROM productos as p" +
                        " inner join categorias as c ON (p.categoria_id=c.id)")
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
        try (PreparedStatement preparedStatement = getConncection().prepareStatement("SELECT p.*,c.nombre as categoria  FROM productos as p " +
                "inner join categorias as c ON (p.categoria_id=c.id) WHERE p.id =?"))
             {
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

        if (product.getId() != null && product.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=?  WHERE id=?";
        } else {
            sql = "INSERT INTO productos (nombre,precio,categoria_id,fecha_registro) VALUES(?,?,?,?)";
        }

        try (PreparedStatement preparedStatement = getConncection().prepareStatement(sql)) {
            preparedStatement.setString(1, product.getNombre());
            preparedStatement.setLong(2, product.getPrecio());
            preparedStatement.setLong(3, product.getCategory().getId());
            if (product.getId() != null && product.getId() > 0) {
                preparedStatement.setLong(4, product.getId());
            } else {
                preparedStatement.setDate(4, new Date(product.getFecha_registro().getTime()));

            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = getConncection().prepareStatement("DELETE FROM productos WHERE id=?")) {
            preparedStatement.setLong(1, id);
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

        Category category=new Category();
        category.setId(resultSet.getLong("categoria_id"));
        category.setNombre(resultSet.getString("categoria"));

        product.setCategory(category);
        return product;
    }
}
