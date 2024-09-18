package org.yanez.java.jdbc.modelo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.yanez.java.jdbc.repositorio.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryRepository implements Repository<Category> {
    private Connection connection;
    @Override
    public void setConn(Connection connection) {
        this.connection=connection;
    }

    @Override
    public List<Category> listAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM categorias")) {
            while (resultSet.next()) {
                categories.add(mapCategory(resultSet));
            }
        }
        return categories;
    }

    @Override
    public Category byId(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categorias WHERE id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCategory(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Category register(Category category) throws SQLException {
        String sql;
        if (category.getId() != null && category.getId() > 0) {
            sql = "UPDATE categorias SET nombre=? WHERE id=?";
        } else {
            sql = "INSERT INTO categorias (nombre) VALUES(?)";
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (category.getId() != null && category.getId() > 0) {
                preparedStatement.setString(1, category.getNombre());
                preparedStatement.setLong(2, category.getId());
            } else {
                preparedStatement.setString(1, category.getNombre());
            }
            preparedStatement.executeUpdate();

            if (category.getId() == null) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        category.setId(rs.getLong(1));
                    }
                }
            }
        }
        return category;

    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categorias WHERE id=?")) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
    }

    private static Category mapCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setNombre(resultSet.getString("nombre"));
        return category;
    }
}
