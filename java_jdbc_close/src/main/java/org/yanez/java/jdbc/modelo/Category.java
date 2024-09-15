package org.yanez.java.jdbc.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre;

}
