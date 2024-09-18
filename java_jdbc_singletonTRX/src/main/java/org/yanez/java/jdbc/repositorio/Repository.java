package org.yanez.java.jdbc.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listAll() throws SQLException;
    T byId(Long id) throws SQLException;
    void register(T t) throws SQLException;
    void delete(Long id) throws SQLException;

}
