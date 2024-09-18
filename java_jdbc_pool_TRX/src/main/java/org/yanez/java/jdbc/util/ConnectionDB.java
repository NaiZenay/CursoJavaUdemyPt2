package org.yanez.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
   private static String url = "jdbc:mysql://localhost:3306/java_curso";
   private static String user = "root";
   private static String pass = "queso";
    static private BasicDataSource pool;

    private static BasicDataSource getInstance() throws SQLException {
        if (pool==null){
            pool= new BasicDataSource();        //configuracion basica
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);
            pool.setInitialSize(3);             //Conexiones disponibles iniciales
            pool.setMinIdle(3);                 //Cantidad minima de conexiones
            pool.setMaxIdle(8);                 //Cantidad maxima de conexiones
            pool.setMaxTotal(8);                //Cantidad maxima de conexiones dipsonibles para ser usadas
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
