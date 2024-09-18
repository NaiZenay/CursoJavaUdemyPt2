package org.yanez.java.jdbc;

import org.yanez.java.jdbc.modelo.Category;
import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try (Connection connection= ConnectionDB.getInstance()){
            Repository<Product> repository= new ProductRepository();

            System.out.println("========== list ==========");
            repository.listAll().forEach(System.out::println);

            System.out.println("========== get by id ==========");
            System.out.println(repository.byId(2L));

            System.out.println("========== new Product ==========");
            Product product= new Product();
            product.setNombre("GPU RTX 4050");
            product.setPrecio(50000);
            product.setFecha_registro(new Date());
            Category category= new Category();
            category.setId(3L);
            product.setCategory(category);
            repository.register(product);
            System.out.println("correctly registered");
            repository.listAll().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}