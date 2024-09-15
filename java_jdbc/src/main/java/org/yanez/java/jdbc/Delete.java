package org.yanez.java.jdbc;

import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;

public class Delete {
    public static void main(String[] args) {
        try (Connection connection= ConnectionDB.getInstance()){
            Repository<Product> repository= new ProductRepository();

            System.out.println("========== list ==========");
            repository.listAll().forEach(System.out::println);

            System.out.println("========== get by id ==========");
            System.out.println(repository.byId(2L));

            System.out.println("========== delete Product ==========");
            repository.delete(3L);
            System.out.println("correctly deleted");
            repository.listAll().forEach(System.out::println);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}