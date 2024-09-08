package org.yanez.java.jdbc;

import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection= ConnectionDB.getInstance()){
            Repository<Product> repository= new ProductRepository();
            repository.listAll().forEach(System.out::println);

            System.out.println(repository.byId(2L));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}