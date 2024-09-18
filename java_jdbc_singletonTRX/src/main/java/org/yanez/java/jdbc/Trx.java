package org.yanez.java.jdbc;

import org.yanez.java.jdbc.modelo.Category;
import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Trx {
    public static void main(String[] args) {
        try (Connection connection = ConnectionDB.getInstance()) {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            try {
                Repository<Product> repository = new ProductRepository();

                System.out.println("========== get by id ==========");
                System.out.println(repository.byId(2L));

                System.out.println("========== new Product ==========");
                Product product = new Product();
                product.setNombre("Disipador");
                product.setPrecio(3000);
                product.setSku("qwerty");
                product.setFecha_registro(new Date());
                Category category = new Category();
                category.setId(3L);
                product.setCategory(category);
                repository.register(product);
                System.out.println("correctly registered");

                repository.listAll().forEach(System.out::println);
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
