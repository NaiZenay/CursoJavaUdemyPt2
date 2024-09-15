package org.yanez.java.jdbc.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listAll();
    T byId(Long id) ;
    void register(T t);
    void delete(Long id);

}
