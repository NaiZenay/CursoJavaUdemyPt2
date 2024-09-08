package org.yanez.java.jdbc.modelo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
