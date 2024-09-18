package org.yanez.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void setConn(Connection connection);
    List<T> listAll() throws SQLException;
    T byId(Long id) throws SQLException;
    T register(T t) throws SQLException;

    void delete(Long id) throws SQLException;
}
