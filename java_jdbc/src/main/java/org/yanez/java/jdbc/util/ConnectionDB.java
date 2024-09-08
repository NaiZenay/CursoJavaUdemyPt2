package org.yanez.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
   private static String url = "jdbc:mysql://localhost:3306/java_curso";
   private static String user = "root";
   private static String pass = "queso";
    static private Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection==null){
            connection=DriverManager.getConnection(url,user,pass);
        }
        return connection;
    }
}
