package org.yanez.userControl.repository;

import org.yanez.userControl.models.User;
import org.yanez.userControl.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {
    @Override
    public void register(User user) {
        try (
                Connection connection = ConnectionDB.getInstance();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name,email,password) VALUES (?,?,?)")
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (
                Connection connection = ConnectionDB.getInstance();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?")
        ) {
            preparedStatement.setLong(1,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        try (
                Connection connection = ConnectionDB.getInstance();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users")
        ) {
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (
                Connection connection = ConnectionDB.getInstance();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name=? ,email=?,password=?, WHERE id=?")
        ) {
            preparedStatement.setLong(4, user.getId());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User byID(Long id) {
        User user = null;
        try (
                Connection connection = ConnectionDB.getInstance();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE users.id=?")
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private static User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
