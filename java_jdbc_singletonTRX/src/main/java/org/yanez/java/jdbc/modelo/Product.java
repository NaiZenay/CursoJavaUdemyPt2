package org.yanez.java.jdbc.modelo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private int precio;

    @Getter
    @Setter
    private Date fecha_registro;

    @Getter
    @Setter
    private Category category;


    @Getter
    @Setter
    private String sku;

    @Override
    public String toString() {
        return "Product: #"+id+
                "\n | nombre: " + nombre +
                " | precio: " + precio +
                " | fecha_registro: " + fecha_registro +
                " | category: " + category.getNombre()+
                " | sku: " + sku +
                " |\n";
    }
}
