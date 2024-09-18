package org.yanez.java.jdbc;

import org.yanez.java.jdbc.modelo.Category;
import org.yanez.java.jdbc.modelo.CategoryRepository;
import org.yanez.java.jdbc.modelo.Product;
import org.yanez.java.jdbc.modelo.ProductRepository;
import org.yanez.java.jdbc.repositorio.Repository;
import org.yanez.java.jdbc.service.CatalogService;
import org.yanez.java.jdbc.service.Service;
import org.yanez.java.jdbc.util.ConnectionDB;

import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        Service service = new CatalogService();
        System.out.println("========== list ==========");
        service.list().forEach(System.out::println);

        System.out.println("========== new Category ==========");
        Category category = new Category();
        category.setNombre("Papeleria");


        System.out.println("========== new Product ==========");
        Product product = new Product();
        product.setNombre("Plumones");
        product.setPrecio(90);
        product.setSku("aerhrdas");
        product.setFecha_registro(new Date());

        service.registerProductANDCategory(product,category);


        service.list().forEach(System.out::println);

    }
}