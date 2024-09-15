package org.yanez.java.jdbc;

import org.yanez.java.jdbc.modelo.Category;
import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Update {
    public static void main(String[] args) {
            Repository<Product> repository= new ProductRepository();

            System.out.println("========== list ==========");
            repository.listAll().forEach(System.out::println);

            System.out.println("========== get by id ==========");
            System.out.println(repository.byId(2L));

            System.out.println("========== update Product ==========");
            Product product= new Product();
            product.setNombre("Split keyboard");
            product.setPrecio(9000);
            product.setFecha_registro(new Date());
            Category category=new Category();
            category.setId(7L);

            repository.register(product);
            System.out.println("correctly registered");
            repository.listAll().forEach(System.out::println);

    }
}